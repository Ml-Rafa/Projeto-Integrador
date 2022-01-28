package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.*;
import org.springframework.stereotype.Service;


import br.com.meli.wave4.entities.Section;
import org.springframework.beans.factory.annotation.Autowired;


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

//    public Integer getTotalProductsInSection(Integer sectionCode){
//        Section section = sectionService.findBySectionCode(sectionCode);
//        return getTotalProductsInSection(section);
//    }

    public Boolean verifyAvailableArea(Integer batchNumber, Section section) {

        Batch batch = batchService.findByBatchNumber(batchNumber);

        Integer total = sectionService.getTotalProductsInSection(section);
        total = total + batch.getCurrentQuantity();

        if (total <= section.getMaxCapacity()){
            return true;
        } else {
            throw new UnavailableSpaceException("Não há espaço suficiente disponível");
        }
    }

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
}