package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.TypeRefrigeration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllBySectionTypeRefrigerated(TypeRefrigeration sectionTypeRefrigerated);
}
