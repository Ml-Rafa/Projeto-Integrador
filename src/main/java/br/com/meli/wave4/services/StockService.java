package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Stock;
import br.com.meli.wave4.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public void save(Stock stock){
        stockRepository.save(stock);
    }
}
