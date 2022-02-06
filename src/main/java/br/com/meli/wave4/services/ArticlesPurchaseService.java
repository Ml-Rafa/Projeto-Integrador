package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ArticlesPurchaseDTO;
import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.services.iservices.IArticlesPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class ArticlesPurchaseService implements IArticlesPurchaseService {

    @Autowired
    ProductService productService;

    @Override
    public BigDecimal calcTotalPrice(Set<ArticlesPurchase> products){
        return products.stream().map(articlesPurchase -> articlesPurchase.getProduct()
                   .getPrice()
                   .multiply(new BigDecimal(articlesPurchase.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Set<ArticlesPurchaseDTO> convertToDTO(Set<ArticlesPurchase> articlesPurchase){
        Set<ArticlesPurchaseDTO> articlesPurchaseDTOSet = new HashSet<>();
        for(ArticlesPurchase a : articlesPurchase){
            articlesPurchaseDTOSet.add(
                ArticlesPurchaseDTO.builder()
                        .product(a.getProduct().getId())
                        .quantity(a.getQuantity())
                        .batchCode(a.getBatchCode())
                        .build()
            );
        }
        return articlesPurchaseDTOSet;
    }

    public Set<ArticlesPurchase> convertToEntity(Set<ArticlesPurchaseDTO> articlesPurchaseDTO){
        Set<ArticlesPurchase> articlesPurchases = new HashSet<>();
        for(ArticlesPurchaseDTO a : articlesPurchaseDTO){
            articlesPurchases.add(
                    ArticlesPurchase.builder()
//                    .product(this.productService.findById(a.getProduct()).orElse(new Product()))
                    .product(this.productService.findById(a.getProduct()))
                    .quantity(a.getQuantity())
                    .batchCode(a.getBatchCode())
                    .build()
            );
        }
        return articlesPurchases;
    }
}
