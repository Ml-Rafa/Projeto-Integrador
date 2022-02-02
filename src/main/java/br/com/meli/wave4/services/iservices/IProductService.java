package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.TypeRefrigeration;

import java.util.List;

public interface IProductService {
    Product findById(Integer productId);

    List<Product> getAll();

    List<Product> findAllByCategory(TypeRefrigeration type);

    Boolean verifyStock(Integer productId, Integer quantity, Integer sectionCode);
}