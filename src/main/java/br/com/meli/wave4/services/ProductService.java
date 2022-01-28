package br.com.meli.wave4.services;

import br.com.meli.wave4.repositories.BatchRepository;
import br.com.meli.wave4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
}
