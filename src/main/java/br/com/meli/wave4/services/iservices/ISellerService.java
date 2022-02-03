package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Seller;

public interface ISellerService {

    Boolean verifyProductOfSeller(Seller seller, Integer productId);
}
