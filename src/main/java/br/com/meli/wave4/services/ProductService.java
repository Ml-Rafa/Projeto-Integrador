package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.TypeRefrigeration;
import br.com.meli.wave4.repositories.ProductRepository;

import br.com.meli.wave4.services.iservices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService implements IProductService
{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findById(Integer productId){
        return productRepository.findById(productId).orElse(new Product());
    }

    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByCategory(TypeRefrigeration type){
        return productRepository.findAllBySectionTypeRefrigerated(type);
    }

    public boolean verifyIfDueDateLessThan3Weeks(Product product){
       return product.getDateValid().isBefore(LocalDate.now().minusDays(20));
    }
}
