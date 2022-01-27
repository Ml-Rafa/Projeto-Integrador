package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface SectionRepository extends JpaRepository<Repository,Integer> {

    Optional<Section> findBySectionCode(Integer sectionCode);
}
