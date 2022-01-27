package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    WarehouseRepository warehouseRepository;


    public Boolean checkProductSection(Integer sectionCode, Integer productId ){

        Product product =  productRepository.findById(productId).orElse(new Product());

        Section section = sectionRepository.findBySectionCode(sectionCode).orElse(new Section());

        return product.getSectionTypeRefrigerated().equals(section.getStorageType());
    }

    public Integer verifyAvailableArea(Section section) {
        return null;
    }

    public Boolean verifyWarehouse(Integer id) {

        Warehouse warehouse = warehouseRepository.findById(id).orElse(null);

        return warehouse !=null;

    }

}
