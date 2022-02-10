package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.DeliveryDates;
import br.com.meli.wave4.entities.DeliveryTimeByStateInHours;
import br.com.meli.wave4.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface DeliveryTimeByStateInHoursRepository extends JpaRepository<DeliveryTimeByStateInHours, Integer> {

    List<DeliveryTimeByStateInHours> findAllByState(String state);

    DeliveryTimeByStateInHours findByStateAndWarehouse(String state, Warehouse warehouse);
//    DeliveryTimeByStateInHours findByStateAndWarehouse(String state, Set<Warehouse> warehouse);
}
