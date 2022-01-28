package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public Section findBySectionCode(Integer sectionCode){
        return sectionRepository.findBySectionCode(sectionCode).orElse(null);
    }

}
