package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InboundOrderService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public boolean verifyWarehouse(Warehouse warehouse) {

        Warehouse warehouseExists = this.warehouseRepository.getById(warehouse.getId());

        Optional<Warehouse> byId = this.warehouseRepository.findById(warehouse.getId());

        return true;
    }

}
