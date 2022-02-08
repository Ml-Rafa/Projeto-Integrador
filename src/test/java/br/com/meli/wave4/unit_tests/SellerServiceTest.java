package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.SellerDTO;
import br.com.meli.wave4.entities.Product;
//import br.com.meli.wave4.entities.Seller;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.exceptions.ProductDoesNotBelongToTheSellerException;
import br.com.meli.wave4.services.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SellerServiceTest {

    @InjectMocks
    SellerService sellerService;

    User user;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        this.user = User
                .builder()
                .name("Pedro")
                .id(30)
                .document("3333333")
                .build();
    }

//    @Test
//    public void verifyProductOfSeller(){
//
//        Set<Product> productList = new HashSet<>();
//        productList.add( Product.builder().id(123).build());
//
//        Seller seller = new Seller();
//        seller.setProductList(productList);
//
//        assertTrue(this.sellerService.verifyProductOfSeller(seller,123));
//
//    }
//
//    @Test
//    public void verifyProductOfSellerReturnFalse(){
//
//        Set<Product> productList = new HashSet<>();
//        productList.add( Product.builder().id(123).build());
//
//        Seller seller = new Seller();
//        seller.setProductList(productList);
//
//        assertFalse(this.sellerService.verifyProductOfSeller(seller,1234));
//
//    }
@Test
public void verifyProductOfSeller(){

    List<Product> productList = new ArrayList<>();
    productList.add( Product.builder().id(123).build());

    User seller = new User();
    seller.setProductList(productList);

    assertTrue(this.sellerService.verifyProductOfSeller(seller,123));

}

    @Test
    public void verifyProductOfSellerReturnFalse(){

        List<Product> productList = new ArrayList<>();
        productList.add( Product.builder().id(123).build());

        User seller = new User();
        seller.setProductList(productList);

        assertThrows(ProductDoesNotBelongToTheSellerException.class,
                ()->this.sellerService.verifyProductOfSeller(seller,1234));

    }

    @Test
    public void convertToDTO(){
        assertInstanceOf(SellerDTO.class, this.sellerService.convertToDTO(this.user));
    }
}
