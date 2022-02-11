package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.services.iservices.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService implements ISectionService {

    @Autowired
    SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository){
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section findBySectionCode(Integer sectionCode){
        Section section = sectionRepository.findBySectionCode(sectionCode);
        if(section == null)
            throw new NotFoundException("Setor não localizado.");
        return section;
    }

    @Override
    public Integer getTotalProductsInSection(Section section){
        return section.getBatchList().stream().mapToInt(Batch::getCurrentQuantity).sum();
    }

    @Override
    public boolean verifySection(Integer sectionCode, Integer warehouseId) {
        Section sectionPersistence = this.findBySectionCode(sectionCode);
        if (sectionPersistence.getWarehouse().getId().equals(warehouseId))
            return true;
        throw new InvalidSectionException();
    }

    @Override
    public boolean verifyBatchInSection(Batch batch, Section section) {
        Integer batchSectionCode = batch.getSection().getSectionCode();
        Integer sectionCode = section.getSectionCode();
        if(batchSectionCode.equals(sectionCode))
            return true;
        throw new InvalidSectionException();
    }
}
