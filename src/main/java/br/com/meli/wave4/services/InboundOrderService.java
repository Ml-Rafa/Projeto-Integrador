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

    @Autowired
    SellerService sellerService;

    @Autowired
    RepresentativeService representativeService;

    @Override
    public Boolean checkProductSection(Integer sectionCode, Integer productId) {

        Product product = productService.findById(productId);
        Section section = sectionService.findBySectionCode(sectionCode);
        if (String.valueOf(product.getSectionTypeRefrigerated()).equals(section.getStorageType())) {
            return true;
        }
        throw new SectionNotMatchTypeProductException();
    }

    @Override
    public Integer getTotalProductsInSection(Section section) {
        return getTotalProductsInSection(section.getBatchList());
    }

    @Override
    public Integer getTotalProductsInSection(List<Batch> batchList) {
        return batchList.stream().mapToInt(Batch::getCurrentQuantity).sum();
    }

    @Override
    public Integer getTotalProductsInSection(Integer sectionCode) {
        Section section = sectionService.findBySectionCode(sectionCode);
        return getTotalProductsInSection(section);
    }

    @Override
    public Boolean verifyAvailableArea(Integer quantityRequired, Section section) {

        Integer total = getTotalProductsInSection(section);
        total = total + quantityRequired;

        if (total <= section.getMaxCapacity()) {
            return true;
        } else {
            throw new UnavailableSpaceException();
        }
    }

    @Override
    public InboundOrder update(InboundOrder inboundOrder) {
        InboundOrder inboundOrderUpdated = inboundOrderRepository.findById(inboundOrder.getOrderNumber()).orElse(null);
        inboundOrderUpdated.setOrderDate(inboundOrder.getOrderDate());
        inboundOrderUpdated.setSection(inboundOrder.getSection());
        inboundOrderUpdated.setSellerId(inboundOrder.getSellerId());
        inboundOrder.getBatchStock().forEach(batch -> batch.setInboundOrder(inboundOrderUpdated));
        inboundOrder.getBatchStock().forEach(batch -> batch.setSection(inboundOrderUpdated.getSection()));
        inboundOrder.getBatchStock().forEach(batch -> batchService.update(batch));
        inboundOrderUpdated.setBatchStock(inboundOrder.getBatchStock());
        inboundOrderUpdated.setOrderNumber(inboundOrder.getOrderNumber());

        return inboundOrderRepository.saveAndFlush(inboundOrderUpdated);
    }

    @Override
    public void registerBatch(List<Batch> batch, InboundOrder inboundOrder) {
        for (Batch b : batch) {
            b.setInboundOrder(inboundOrder);
        }
        batchService.saveAll(batch);
    }

    @Override
    public void registerBatch(Batch batch) {
        batchService.save(batch);
    }

    @Override
    public InboundOrder create(InboundOrder inboundOrder) {

//      O ARMAZEM É VÁLIDO
        Warehouse warehouse = this.getWarehouse(inboundOrder);

//      É VALIDADO SE O SETOR É VÁLIDO
        Section section = this.getSection(inboundOrder);

        this.checkSectionOfWarehouse(warehouse, section);

//      VERIFICA SE O ID DO PRODUTO ESTÁ REGISTRADO EM NOME DO VENDEDOR
        inboundOrder.getBatchStock().forEach(batch -> sellerService.productBelongToTheSeller(inboundOrder, batch));

//      VALIDA SE O PRODUTO ESTÁ NO SETOR CORRETO E O REPRESENTANTE
        inboundOrder.getBatchStock().forEach(batch -> {
            User representative = AuthenticationService.authenticated();
            representativeService.checkRepresentativeOfWarehouse(warehouse, representative);
            checkProductSection(inboundOrder.getSection().getSectionCode(), batch.getProduct().getId());
            batch.setRepresentative(representative);
            batch.setSection(section);
        });

//      VALIDA O ESPAÇO
        Integer totalItens = this.getTotalProductsInSection(inboundOrder.getBatchStock());
        verifyAvailableArea(totalItens, section);

//      REGISTRA INBOUND ORDER
        InboundOrder i = this.inboundOrderRepository.save(inboundOrder);

//      REGISTRA O LOTE
        registerBatch(inboundOrder.getBatchStock(), i);

        //busca novamente porque a lista já está atualizada
        Section sectionAfterInsert = sectionService.findBySectionCode(inboundOrder.getSection().getSectionCode());
        Set<Product> productSet = new HashSet<>();
        inboundOrder.getBatchStock().forEach(batch -> {
            productSet.add(batch.getProduct());
        });
        return inboundOrder;
    }

    @Override
    public void checkSectionOfWarehouse(Warehouse warehouse, Section section) {
        if (!warehouse.getId().equals(section.getWarehouse().getId())) {
            throw new InvalidSectionException();
        }
    }

    @Override
    public Warehouse getWarehouse(InboundOrder inboundOrder) {
        Warehouse warehouse = warehouseService.findById(inboundOrder.getWarehouse().getId());
        if (warehouse == null)
            throw new InvalidWarehouseException();
        return warehouse;
    }
    @Override
    public Section getSection(InboundOrder inboundOrder) {
        Section section = sectionService.findBySectionCode(inboundOrder.getSection().getSectionCode());
        if (section == null)
            throw new InvalidSectionException();
        return section;
    }
    @Override
    public User getRepresentative(Batch batch) {
        User representative = representativeService.findById(batch.getRepresentative().getId());
        if (representative == null)
            throw new RepresentativeNotCorrespondentException();
        return representative;
    }

    public InboundOrder convertToEntity(InboundOrderDTO inboundOrderDTO) {
        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        List<Batch> batchList = batchDTOList.stream().map(batchService::convertToEntity).collect(Collectors.toList());
        return InboundOrder.builder()
                .orderNumber(inboundOrderDTO.getOrderNumber())
                .orderDate(inboundOrderDTO.getOrderDate())
                .section(this.sectionService.findBySectionCode(inboundOrderDTO.getSectionCode()))
                .batchStock(batchList)
                .sellerId(inboundOrderDTO.getSellerId())
                .warehouse(Warehouse.builder().id(inboundOrderDTO.getWarehouseCode()).build())
//                .warehouse(warehouseService.findById(inboundOrderDTO.getWarehouseCode()))
                .build();
    }

    @Override
    public InboundOrder findById(Integer inboundOrderID) {
        InboundOrder inboundOrder = this.inboundOrderRepository.findById(inboundOrderID).orElse(null);
        if (inboundOrder == null)
            throw new NotFoundException("InboundOrder não encontrado.");
        return inboundOrder;
    }
}