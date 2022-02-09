package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.entities.ArticlesPurchase;

import java.math.BigDecimal;
import java.util.List;

public interface IArticlesPurchaseService {
    BigDecimal calcTotalPrice(List<ArticlesPurchase> products);
}
