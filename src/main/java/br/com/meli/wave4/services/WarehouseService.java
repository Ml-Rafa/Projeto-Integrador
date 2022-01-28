package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Warehouse;
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
}
