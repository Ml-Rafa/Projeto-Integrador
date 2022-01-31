package br.com.meli.wave4.services;

import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.services.iservices.IArticlesPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
}
