package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.Client;

public interface IClientService {

    public Client findById(Integer clientId);
}
