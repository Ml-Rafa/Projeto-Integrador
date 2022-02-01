package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.DTO.InboundOrderDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.*;
import br.com.meli.wave4.repositories.InboundOrderRepository;
import br.com.meli.wave4.services.iservices.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    ProductService productService;

    @Autowired
    SectionService sectionService;

    @Autowired
    BatchService batchService;

    @Autowired
    WarehouseService warehouseService;


    @Autowired
    InboundOrderRepository inboundOrderRepository;

    @Override
    public Boolean checkProductSection(Integer sectionCode, Integer productId) {

        Product product = productService.findById(productId);

        Section section = sectionService.findBySectionCode(sectionCode);
        System.out.println("==================================");
        System.out.println("product: " + product.getSectionTypeRefrigerated());
        System.out.println("section: " + section.getStorageType());

        if(String.valueOf(product.getSectionTypeRefrigerated()).equals(section.getStorageType())){
            return true;
        }
        throw new SectionNotMatchTypeProductException();
    }


    @Override
    public Integer getTotalProductsInSection(Section section){
        return getTotalProductsInSection(section.getBatchList());
    }

    @Override
    public Integer getTotalProductsInSection(List<Batch> batchList){
        return batchList.stream().mapToInt(Batch::getCurrentQuantity).sum();
    }

    @Override
    public Integer getTotalProductsInSection(Integer sectionCode){
        Section section = sectionService.findBySectionCode(sectionCode);
        return getTotalProductsInSection(section);
    }

    @Override
    public Boolean verifyAvailableArea(Integer quantityRequired, Section section) {

        Integer total = getTotalProductsInSection(section);
        total = total + quantityRequired;

        if (total <= section.getMaxCapacity()){
            return true;
        } else {
            throw new UnavailableSpaceException("Não há espaço suficiente disponível");
        }
    }

    @Override
    public InboundOrder saveInboundOrder(InboundOrder inboundOrder) {

        try{
            System.out.println();
            warehouseService.verifyWarehouse(inboundOrder.getSection().getWarehouse().getId());
//            inboundOrder.getBatchStock().forEach(el ->
//                    checkProductSection(inboundOrder.getSection().getSectionCode(),el.getProduct().getId())
//            );

            sectionService.verifySection(inboundOrder.getSection().getSectionCode(), inboundOrder.getSection().getWarehouse().getId());
//            verifyAvailableArea();
        } catch (UnregisteredProductException |
                InvalidWarehouseException |
                RepresentativeNotCorrespondentException |
                InvalidSectionException |
                SectionNotMatchTypeProductException |
                UnavailableSpaceException e){
            return null;
        }
        return inboundOrder;
    }

    @Override
    public InboundOrder updateById(InboundOrder inboundOrder) {
        InboundOrder inboundOrderUpdated = inboundOrderRepository.findById(inboundOrder.getOrderNumber()).orElse(new InboundOrder());
        inboundOrderUpdated.setOrderDate(inboundOrder.getOrderDate());
        inboundOrderUpdated.setSection(inboundOrder.getSection());
        inboundOrder.getBatchStock().forEach(batch -> batch.setInboundOrder(inboundOrderUpdated));
        inboundOrder.getBatchStock().forEach(batch -> batch.setSection(inboundOrderUpdated.getSection()));
        inboundOrder.getBatchStock().forEach(batch -> batchService.update(batch));
        inboundOrderUpdated.setBatchStock(inboundOrder.getBatchStock());
        inboundOrderUpdated.setOrderNumber(inboundOrder.getOrderNumber());

        return inboundOrderRepository.saveAndFlush(inboundOrderUpdated);

    }

    @Override
    public void registerBatch(List<Batch> batch, InboundOrder inboundOrder) {
        for(Batch b: batch){
            b.setInboundOrder(inboundOrder);
        }
        batchService.saveAll(batch);
    }

    @Override
    public void registerBatch(Batch batch){
        batchService.save(batch);
    }

    @Override
    public InboundOrder create(InboundOrder inboundOrder){

//      AQUI É VALIDADO SE O SETOR E O ARMAZEM SÃO VÁLIDOS
        Section section = sectionService.findBySectionCode(inboundOrder.getSection().getSectionCode());

        if (section == null){
            throw new InvalidSectionException("Este setor não é válido!");
        } else{
//          AQUI ASSEGURA QUE O REPRESENTANTE ESTÁ LIGADO AO ARMAZEM
            Representative representative = section.getWarehouse().getRepresentative();

//          VALIDA SE O PRODUTO ESTÁ NO SETOR CORRETO
            inboundOrder.getBatchStock().forEach(batch -> {
                checkProductSection(inboundOrder.getSection().getSectionCode(), batch.getProduct().getId());
                batch.setRepresentative(representative);
                batch.setSection(section);
            });

//          VALIDA O ESPAÇO
            Integer totalItens = getTotalProductsInSection(inboundOrder.getBatchStock());
            verifyAvailableArea(totalItens, section);
//          REGISTRA INBOUND ORDER
            InboundOrder i = inboundOrderRepository.save(inboundOrder);
            System.out.println("INBOUND ORDER: " + i.getOrderNumber());

//          REGISTRA O LOTE

            registerBatch(inboundOrder.getBatchStock(), i);

            //busca novamente porque a lista já está atualizada
            Section sectionAfterInsert = sectionService.findBySectionCode(inboundOrder.getSection().getSectionCode());
            Set<Product> productSet = new HashSet<>();
            inboundOrder.getBatchStock().forEach(batch -> {
                productSet.add(batch.getProduct());
            });



        }
        return inboundOrder;
    }
    public  InboundOrder convertToEntity(InboundOrderDTO inboundOrderDTO) {
        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        List<Batch> batchList = batchDTOList.stream().map(batchService::convertToEntity).collect(Collectors.toList());
        return InboundOrder.builder()
                .orderNumber(inboundOrderDTO.getOrderNumber())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(this.sectionService.findBySectionCode(inboundOrderDTO.getSectionCode()))
                .batchStock(batchList)
                .build();
    }

    public InboundOrder findyById(Integer inboundOrderID){
        return this.inboundOrderRepository.findById(inboundOrderID).orElse(null);
    }
}