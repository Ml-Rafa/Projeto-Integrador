package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ArticlesPurchaseDTO;
import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.repositories.ArticlesPurchaseRepository;
import br.com.meli.wave4.services.iservices.IArticlesPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesPurchaseService implements IArticlesPurchaseService {

    @Autowired
    ProductService productService;

    @Autowired
    ArticlesPurchaseRepository articlesPurchaseRepository;

    @Override
    public BigDecimal calcTotalPrice(List<ArticlesPurchase> products) {
        return products.stream().map(articlesPurchase -> articlesPurchase.getProductArticle()
                .getPrice()
                .multiply(new BigDecimal(articlesPurchase.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ArticlesPurchaseDTO> convertToDTO(List<ArticlesPurchase> articlesPurchase) {
        List<ArticlesPurchaseDTO> articlesPurchaseDTOSet = new ArrayList<>();
        for (ArticlesPurchase a : articlesPurchase) {
            articlesPurchaseDTOSet.add(
                    ArticlesPurchaseDTO.builder()
                            .id(a.getId())
                            .product(a.getProductArticle().getId())
                            .quantity(a.getQuantity())
                            .batchCode(a.getBatchCode())
                            .build()
            );
        }
        return articlesPurchaseDTOSet;
    }

    public List<ArticlesPurchase> convertToEntity(List<ArticlesPurchaseDTO> articlesPurchaseDTO) {
        List<ArticlesPurchase> articlesPurchases = new ArrayList<>();
        for (ArticlesPurchaseDTO a : articlesPurchaseDTO) {
            articlesPurchases.add(
                    ArticlesPurchase.builder()
                            .id(a.getId())
                            .productArticle(this.productService.findById(a.getProduct()))
                            .quantity(a.getQuantity())
                            .batchCode(a.getBatchCode())
                            .build()
            );
        }
        return articlesPurchases;
    }

    public void save(ArticlesPurchase articlesPurchase) {
        articlesPurchaseRepository.save(articlesPurchase);
    }

    public ArticlesPurchase findById(Integer articlesPurchaseId) {
        return articlesPurchaseRepository.findById(articlesPurchaseId).orElse(null);
    }

}
