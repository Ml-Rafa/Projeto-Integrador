package br.com.meli.wave4.services;

import br.com.meli.wave4.repositories.RepresentativeRepository;
import br.com.meli.wave4.services.iservices.IRepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements IRepresentativeService {

    @Autowired
    RepresentativeRepository representativeRepository;
}
