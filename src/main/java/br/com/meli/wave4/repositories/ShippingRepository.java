package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Integer> {
}
