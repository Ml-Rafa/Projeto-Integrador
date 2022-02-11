package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

    Batch findByBatchNumber(Integer batchNumber);

    @Modifying
    @Transactional
    @Query("update Batch b set b.currentQuantity = ?1 where b.id = ?2")
    void reverseStock(Integer quantity, Integer batchNumber);

    @Modifying
    @Transactional
    @Query("update Batch b set b.currentQuantity = ?1 where b.id = ?2")
    void update(Integer quantity, Integer batchNumber);
}
