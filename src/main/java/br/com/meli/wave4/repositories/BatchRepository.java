package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

//    Optional<Batch> findByBatchNumber(Integer batchNumber);
    Batch findByBatchNumber(Integer batchNumber);
}
