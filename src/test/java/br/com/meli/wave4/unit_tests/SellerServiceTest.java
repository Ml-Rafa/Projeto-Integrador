package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Seller;
import br.com.meli.wave4.services.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SellerServiceTest {

    @InjectMocks
    SellerService sellerService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void verifyProductOfSeller(){

        Set<Product> productList = new HashSet<>();
        productList.add( Product.builder().id(123).build());

        Seller seller = new Seller();
        seller.setProductList(productList);

        assertTrue(this.sellerService.verifyProductOfSeller(seller,123));

    }

    @Test
    public void verifyProductOfSellerReturnFalse(){

        Set<Product> productList = new HashSet<>();
        productList.add( Product.builder().id(123).build());

        Seller seller = new Seller();
        seller.setProductList(productList);

        assertFalse(this.sellerService.verifyProductOfSeller(seller,1234));

    }
}
