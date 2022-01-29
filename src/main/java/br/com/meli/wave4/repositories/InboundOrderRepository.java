package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Integer> {
}
