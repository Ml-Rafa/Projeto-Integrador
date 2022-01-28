package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.*;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;


import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.meli.wave4.entities.Product;

@Service
public class InboundOrderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    WarehouseRepository warehouseRepository;


    public Boolean checkProductSection(Integer sectionCode, Integer productId) {

        Product product = productRepository.findById(productId).orElse(new Product());

        Section section = sectionRepository.findBySectionCode(sectionCode).orElse(new Section());

        if(product.getSectionTypeRefrigerated().equals(section.getStorageType())){
            return true;
        }
        throw new SectionNotMatchTypeProductException("Setor não responsável pelo armazenamento do tipo deste produto.");
    }

    public Integer verifyAvailableArea(Section section) { return null;
    }

    public Boolean verifyWarehouse(Integer id) {

        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);

        if (warehouse == null) {
            throw new InvalidWarehouseException("Armazém inválido.");
        }
        return true;

    }

    public boolean verifySection(Integer sectionCode) {
        Section section = sectionRepository.findBySectionCode(sectionCode).orElse(null);

        if (section == null) {
            throw new InvalidSectionException("Setor inválido.");
        }
        return true;
    }

    public InboundOrder saveInboundOrder(InboundOrder inboundOrder) {
        try{
            System.out.println();
//            verifyWarehouse();
//            checkProductSection();
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

}
