package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.ArticlesPurchase;

import java.math.BigDecimal;
import java.util.Set;

public interface IArticlesPurchaseService {
    BigDecimal calcTotalPrice(Set<ArticlesPurchase> products);
}
