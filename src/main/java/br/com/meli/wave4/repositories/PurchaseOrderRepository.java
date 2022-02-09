package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {

    List<PurchaseOrder> findByClientId(Integer clientId);


}
