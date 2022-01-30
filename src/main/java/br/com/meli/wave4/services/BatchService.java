package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.repositories.BatchRepository;
import br.com.meli.wave4.services.iservices.IBatchService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class BatchService implements IBatchService {

    @Autowired
    private BatchRepository batchRepository;

    public BatchService(BatchRepository batchRepository){
        this.batchRepository = batchRepository;
    }
    @Override
    public Batch findByBatchNumber(Integer batchNumber){
       return batchRepository.findByBatchNumber(batchNumber).orElse(new Batch());
    }

    @Override
    public List<Batch> saveAll(List<Batch> batchList){
        return batchRepository.saveAll(batchList);
    }

    @Override
    public void save(Batch batch){
        batchRepository.save(batch);
    }

}
