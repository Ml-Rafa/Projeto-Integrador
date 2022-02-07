package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Section;

import java.util.Optional;

public interface ISectionService {
//    Optional<Section> findBySectionCode(Integer sectionCode);
    Section findBySectionCode(Integer sectionCode);

    Integer getTotalProductsInSection(Section section);

    boolean verifySection(Integer sectionCode, Integer warehouseId);

    boolean verifyBatchInSection(Batch batch, Section section);
}
