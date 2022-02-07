package br.com.meli.wave4.services.iservices;

//import br.com.meli.wave4.entities.Seller;
import br.com.meli.wave4.entities.User;

public interface ISellerService {

//    Boolean verifyProductOfSeller(Seller seller, Integer productId);
    Boolean verifyProductOfSeller(User seller, Integer productId);
}
