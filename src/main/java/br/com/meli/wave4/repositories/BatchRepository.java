package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
}
