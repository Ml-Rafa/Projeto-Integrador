package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseOrderService {

    @Autowired
    ArticlesPurchaseService articlesPurchaseService;


    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDTO.builder()
                .totalPrice(articlesPurchaseService.calcTotalPrice(purchaseOrder.getArticlesPurchases()))
                .build();
    }
}
