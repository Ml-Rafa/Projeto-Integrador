package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IBatchService {
//    Optional<Batch> findByBatchNumber(Integer batchNumber);
    Batch findByBatchNumber(Integer batchNumber);

    List<Batch> saveAll(List<Batch> batchList);

    Batch save(Batch batch);

    void update(Batch batch);

    boolean verifyBatchContainsProduct(Batch batch, Product product);

    void updateStock(Integer productId, Integer quantity, Integer sectionCode);
}
