package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findByUser(String user);
    User findByUser(String user);
}
