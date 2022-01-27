package br.com.meli.wave4.services;

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

    public Integer verifyAvailableArea(Section section) {
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
