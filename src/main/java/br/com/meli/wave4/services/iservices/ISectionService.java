package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Section;

public interface ISectionService {
    Section findBySectionCode(Integer sectionCode);

    Integer getTotalProductsInSection(Section section);

    boolean verifySection(Integer sectionCode, Integer warehouseId);
}
