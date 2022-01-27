package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InboundOrderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SectionRepository sectionRepository;


    public Boolean checkProductSection(Integer sectionCode, Integer productId ){

        Product product =  productRepository.findById(productId).orElse(new Product());

        Section section = sectionRepository.findBySectionCode(sectionCode).orElse(new Section());

        return product.getSectionTypeRefrigerated().equals(section.getStorageType());
    }

    public Boolean verifyAvailableArea(Batch batch, Section section) {
        return null;
    }


    @Autowired
    WarehouseRepository warehouseRepository;

    public boolean verifyWarehouse(Warehouse warehouse) {

        Warehouse warehouseExists = this.warehouseRepository.getById(warehouse.getId());

        Optional<Warehouse> byId = this.warehouseRepository.findById(warehouse.getId());

        return true;
    }

}
