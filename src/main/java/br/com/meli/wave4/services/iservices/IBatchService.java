package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Batch;

import java.util.List;

public interface IBatchService {
    Batch findByBatchNumber(Integer batchNumber);

    List<Batch> saveAll(List<Batch> batchList);

    void save(Batch batch);
}
