package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.InvalidWarehouseException;
import br.com.meli.wave4.repositories.WarehouseRepository;
import br.com.meli.wave4.services.iservices.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(Integer id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }


    @Override
    public Boolean verifyWarehouse(Integer id) {
        Warehouse warehouse = this.findById(id);
        if (warehouse == null) {
            throw new InvalidWarehouseException();
        }
        return true;
    }
}
