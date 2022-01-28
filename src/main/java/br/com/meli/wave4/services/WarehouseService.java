package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.InvalidWarehouseException;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Warehouse findById(Integer id){
        return warehouseRepository.findById(id).orElse(null);
    }

    public Boolean verifyWarehouse(Integer id) {
        Warehouse warehouse = this.findById(id);
        if (warehouse == null) {
            throw new InvalidWarehouseException();
        }
        return true;
    }
}
