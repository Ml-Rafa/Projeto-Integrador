package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public Section findBySectionCode(Integer sectionCode){
        return sectionRepository.findBySectionCode(sectionCode).orElse(null);
    }

    public Integer getTotalProductsInSection(Section section){
        return section.getBatchList().stream().mapToInt(Batch::getCurrentQuantity).sum();
    }

    public boolean verifySection(Integer sectionCode, Integer warehouseId) {
        Section sectionPersistence = this.findBySectionCode(sectionCode);

        if (sectionPersistence != null || sectionPersistence.getWarehouse().getId().equals(warehouseId)) {
            return true;
        }
        throw new InvalidSectionException();
    }
}
