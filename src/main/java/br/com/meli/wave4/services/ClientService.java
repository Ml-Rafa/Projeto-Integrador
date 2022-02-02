package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.Client;
import br.com.meli.wave4.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client findById(Integer clientId){
        return this.clientRepository.findById(clientId).orElse(null);
    }

}
