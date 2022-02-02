package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Warehouse;

import java.util.List;

public interface IWarehouseService {
    Warehouse findById(Integer id);

    Boolean verifyWarehouse(Integer id);

    List<Warehouse> findAll();
}
