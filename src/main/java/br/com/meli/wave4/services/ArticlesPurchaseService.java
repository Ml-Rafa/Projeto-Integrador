package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ArticlesPurchaseDTO;
import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.services.iservices.IArticlesPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticlesPurchaseService implements IArticlesPurchaseService {

    @Autowired
    ProductService productService;

        @Override
        public BigDecimal calcTotalPrice(List<ArticlesPurchase> products){
        return products.stream().map(articlesPurchase -> articlesPurchase.getProduct()
                   .getPrice()
                   .multiply(new BigDecimal(articlesPurchase.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ArticlesPurchaseDTO> convertToDTO(List<ArticlesPurchase> articlesPurchase){
        List<ArticlesPurchaseDTO> articlesPurchaseDTOSet = new ArrayList<>();
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

    public List<ArticlesPurchase> convertToEntity(List<ArticlesPurchaseDTO> articlesPurchaseDTO){
        List<ArticlesPurchase> articlesPurchases = new ArrayList<>();
        for(ArticlesPurchaseDTO a : articlesPurchaseDTO){
            articlesPurchases.add(
                    ArticlesPurchase.builder()
                    .product(this.productService.findById(a.getProduct()))
                    .quantity(a.getQuantity())
                    .batchCode(a.getBatchCode())
                    .build()
            );
        }
        return articlesPurchases;
    }
}
