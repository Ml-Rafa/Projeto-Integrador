package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Integer> {


}
