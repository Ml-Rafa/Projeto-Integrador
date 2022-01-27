package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping,Integer> {
}
