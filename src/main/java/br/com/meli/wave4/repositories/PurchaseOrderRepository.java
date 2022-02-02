package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository  extends JpaRepository<PurchaseOrder, Integer> {


}
