package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Seller;
import br.com.meli.wave4.exceptions.ProductDoesNotBelongToTheSellerException;
import br.com.meli.wave4.repositories.SellerRepository;
import br.com.meli.wave4.services.iservices.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Boolean verifyProductOfSeller(Seller seller, Integer productId){
        List<Product> productList = seller.getProductList().stream().filter(p -> p.getId().equals(productId)).collect(Collectors.toList());
        if(productList.isEmpty())
            throw new ProductDoesNotBelongToTheSellerException();
        return true;
    }
}
