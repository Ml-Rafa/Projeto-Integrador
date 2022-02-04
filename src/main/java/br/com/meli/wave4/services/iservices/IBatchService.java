package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Product;

import java.util.List;

public interface IBatchService {
    Batch findByBatchNumber(Integer batchNumber);

    List<Batch> saveAll(List<Batch> batchList);

    Batch save(Batch batch);

    void update(Batch batch);

    boolean verifyBatchContainsProduct(Batch batch, Product product);

    void updateStock(Integer productId, Integer quantity, Integer sectionCode);
}
