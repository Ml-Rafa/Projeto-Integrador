package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Stock;
import br.com.meli.wave4.repositories.StockRepository;
import br.com.meli.wave4.services.iservices.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService {

    @Autowired
    StockRepository stockRepository;

    @Override
    public void save(Stock stock){
        stockRepository.save(stock);
    }
}
