package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
public class InboundOrderService {


    Section section;

    public boolean verifSection(Integer sectionCode) {
//
        Section.findByCode(sectionCode);
        if (sectionCode == null) {
            return false;
        }
        return true;

    }


}
