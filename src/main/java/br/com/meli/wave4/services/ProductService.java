package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.TypeRefrigeration;
import br.com.meli.wave4.repositories.BatchRepository;
import br.com.meli.wave4.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product findById(Integer productId){
        return productRepository.findById(productId).orElse(new Product());
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(TypeRefrigeration type){
        return productRepository.findAllBySectionTypeRefrigerated(type);
    }
}
