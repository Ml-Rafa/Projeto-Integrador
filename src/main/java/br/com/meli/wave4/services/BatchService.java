package br.com.meli.wave4.services;

import br.com.meli.wave4.repositories.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    @Autowired
    BatchRepository batchRepository;

}
