package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Representative;
import br.com.meli.wave4.repositories.RepresentativeRepository;
import br.com.meli.wave4.services.iservices.IRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements IRepresentativeService {

    @Autowired
    RepresentativeRepository representativeRepository;

    public Representative findById(Integer representativeId){
        return representativeRepository.findById(representativeId).orElse(new Representative());
    }

}
