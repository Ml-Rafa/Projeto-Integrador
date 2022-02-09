package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.DeliveryDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDatesRepository extends JpaRepository<DeliveryDates, Integer> {
}
