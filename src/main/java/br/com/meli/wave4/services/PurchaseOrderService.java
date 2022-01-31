package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.services.iservices.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    ArticlesPurchaseService articlesPurchaseService;


    @Override
    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDTO.builder()
                .totalPrice(articlesPurchaseService.calcTotalPrice(purchaseOrder.getArticlesPurchases()))
                .build();
    }

    public PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {
        return PurchaseOrder.builder().build();
    }
}
