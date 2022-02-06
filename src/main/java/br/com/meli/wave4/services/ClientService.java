package br.com.meli.wave4.services;

//import br.com.meli.wave4.entities.Client;
import br.com.meli.wave4.entities.User;
//import br.com.meli.wave4.repositories.ClientRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientService implements IClientService {


    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Integer clientId){
        return this.userRepository.findById(clientId).orElse(null);
    }

//    @Autowired
//    ClientRepository clientRepository;
//
//    @Override
//    public Client findById(Integer clientId){
//        return this.clientRepository.findById(clientId).orElse(null);
//    }

}
