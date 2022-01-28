package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.repositories.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    @Autowired
    BatchRepository batchRepository;

    public Batch findByBatchNumber(Integer batchNumber){
       return batchRepository.findByBatchNumber(batchNumber).orElse(new Batch());
    }

    public List<Batch> saveAll(List<Batch> batchList){
        return batchRepository.saveAll(batchList);

    }

    public void save(Batch batch){
        batchRepository.save(batch);
    }

}
