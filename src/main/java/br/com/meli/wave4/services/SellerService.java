package br.com.meli.wave4.services;

import br.com.meli.wave4.repositories.SellerRepository;
import br.com.meli.wave4.services.iservices.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;
}
