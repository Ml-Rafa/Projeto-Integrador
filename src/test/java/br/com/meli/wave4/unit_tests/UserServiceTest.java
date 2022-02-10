package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.UserDTO;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    UserDTO user;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        user = UserDTO
                .builder()
                .id(10)
                .name("Maria")
                .document("44444")
                .address("Rua 123")
                .telephone("222222")
                .build();
    }

    @Test
    public void convertToEntity(){
        assertInstanceOf(User.class, this.userService.convertToEntity(this.user));
    }

}
