package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouseService {
//    Optional<Warehouse> findById(Integer id);
    Warehouse findById(Integer id);

    Boolean verifyWarehouse(Integer id);

    List<Warehouse> findAll();
}
