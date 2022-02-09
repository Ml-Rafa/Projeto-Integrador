package br.com.meli.wave4.services.iservices;

//import br.com.meli.wave4.entities.Client;

import br.com.meli.wave4.entities.User;

public interface IClientService {

//    public Client findById(Integer clientId);
//    Optional<User> findById(Integer clientId);
    User findById(Integer clientId);
}
