package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ArticlesPurchaseService {

    @Autowired
    ProductService productService;

    public BigDecimal calcTotalPrice(Set<ArticlesPurchase> products){
        return products.stream().map(articlesPurchase -> productService.findById(articlesPurchase.getProductId())
                   .getPrice()
                   .multiply(new BigDecimal(articlesPurchase.getQuantity()))
        ).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
