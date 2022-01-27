package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
