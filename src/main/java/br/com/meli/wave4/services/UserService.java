package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.UserDTO;
import br.com.meli.wave4.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User convertToEntity(UserDTO user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .build();
    }
}
