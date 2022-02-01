package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Seller;

public interface ISellerService {

    Boolean verifyProducOfSeller(Seller seller, Integer productId);
}
