package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.ArticlesPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ArticlesPurchaseRepository extends JpaRepository<ArticlesPurchase, Integer> {

}
