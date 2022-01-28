package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.*;
import br.com.meli.wave4.repositories.*;
import org.springframework.stereotype.Service;


import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@Service
public class InboundOrderService {

    @Autowired
    ProductService productService;

    @Autowired
    SectionService sectionService;

    @Autowired
    BatchService batchService;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    StockService stockService;

    public Boolean checkProductSection(Integer sectionCode, Integer productId) {

        Product product = productService.findById(productId);

        Section section = sectionService.findBySectionCode(sectionCode);

        if(product.getSectionTypeRefrigerated().equals(section.getStorageType())){
            return true;
        }
        throw new SectionNotMatchTypeProductException();
    }


    public Integer getTotalProductsInSection(Section section){
        return section.getBatchList().stream().mapToInt(Batch::getCurrentQuantity).sum();
    }

    public Integer getTotalProductsInSection(Integer sectionCode){
        Section section = sectionService.findBySectionCode(sectionCode);
        return getTotalProductsInSection(section);
    }

    public Boolean verifyAvailableArea(Integer batchNumber, Section section) {

        Batch batch = batchService.findByBatchNumber(batchNumber);

        Integer total = getTotalProductsInSection(section);
        total = total + batch.getCurrentQuantity();

        if (total <= section.getMaxCapacity()){
            return true;
        } else {
            throw new UnavailableSpaceException("Não há espaço suficiente disponível");
        }
    }

    public Boolean verifyWarehouse(Integer id) {

        Warehouse warehouse = warehouseService.findById(id);

        if (warehouse == null) {
            throw new InvalidWarehouseException();
        }
        return true;

    }

    public boolean verifySection(Integer sectionCode) {
        Section section = sectionService.findBySectionCode(sectionCode);

        if (section == null) {
            throw new InvalidSectionException();
        }
        return true;
    }

    public InboundOrder saveInboundOrder(InboundOrder inboundOrder) {
        try{
            System.out.println();
//            verifyWarehouse(inboundOrder.getSection().getWarehouse().getId());
//            inboundOrder.getBatchStock().forEach(el ->
//                    checkProductSection(inboundOrder.getSection().getSectionCode(),el.getProduct().getId())
//            );

//            verifySection();
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

    public void registerBatch(Batch batch){
        batchService.save(batch);
    }
}