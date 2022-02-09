package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.RecurrentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecurrentOrderRepository extends JpaRepository<RecurrentOrder, Integer> {

    List<RecurrentOrder>  findByNextDateAndActive(LocalDate nextDate, Boolean active);


}
