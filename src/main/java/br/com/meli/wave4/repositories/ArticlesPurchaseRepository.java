package br.com.meli.wave4.repositories;

import br.com.meli.wave4.entities.ArticlesPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesPurchaseRepository extends JpaRepository<ArticlesPurchase, Integer> {

//    @Modifying
//    @Transactional
//    @Query("update ArticlesPurchase a set a.batchCode = ?1, a.productArticle = ?2, a.quantity = ?3 where a.id = ?4")
//    void updateArticlesPurchase(Integer batchNumber, Integer productId, Integer quantity, Integer articlesPurchaseId);
}
