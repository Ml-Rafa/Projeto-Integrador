package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {

    Optional<Section> findBySectionCode(Integer sectionCode);


}
