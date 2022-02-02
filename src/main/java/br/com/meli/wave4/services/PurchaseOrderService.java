package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.repositories.ClientRepository;
import br.com.meli.wave4.repositories.PurchaseOrderRepository;
import br.com.meli.wave4.services.iservices.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    ArticlesPurchaseService articlesPurchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    BatchService batchService;


    @Override
    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDTO.builder()
                .id(purchaseOrder.getId())
                .date(purchaseOrder.getDate())
                .clientId(purchaseOrder.getClient().getId())
                .orderStatus(purchaseOrder.getOrderStatus())
                .articlesPurchases(this.articlesPurchaseService.convertToDTO(purchaseOrder.getArticlesPurchases()))
                .totalPrice(articlesPurchaseService.calcTotalPrice(purchaseOrder.getArticlesPurchases()))
                .build();
    }

    public PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {

        return PurchaseOrder.builder()
                .client(this.clientService.findById(purchaseOrderDTO.getClientId()))
                .orderStatus(purchaseOrderDTO.getOrderStatus())
                .date(purchaseOrderDTO.getDate())
                .id(purchaseOrderDTO.getId())
                .articlesPurchases(this.articlesPurchaseService.convertToEntity(purchaseOrderDTO.getArticlesPurchases()))
                .build();
    }


    public PurchaseOrderDTO order(PurchaseOrder purchaseOrder){

        Set<ArticlesPurchase> products = new HashSet<>();

        for(ArticlesPurchase a: purchaseOrder.getArticlesPurchases()){

            Product p = this.productService.findById(a.getProduct().getId());

            Client client = (this.clientService.findById(purchaseOrder.getClient().getId()));
            Boolean haveStock =
                    this.productService.verifyStock(p.getId(),a.getQuantity(),a.getBatchCode());
            Boolean lessThan3weak = this.productService.verifyIfDueDateLessThan3Weeks(p);

            if(haveStock && lessThan3weak && client != null ){
                products.add(a);
                this.batchService.updateStock(p.getId(),a.getQuantity(),a.getBatchCode());
            }
        }

        purchaseOrder.setTotalPrice(this.articlesPurchaseService.calcTotalPrice(products));
        purchaseOrder.setArticlesPurchases(products);

        return this.convertToDTO(this.purchaseOrderRepository.save(purchaseOrder));
    }
}
