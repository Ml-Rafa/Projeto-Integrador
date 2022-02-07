package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.*;
//import br.com.meli.wave4.repositories.ClientRepository;
import br.com.meli.wave4.repositories.PurchaseOrderRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseOrderService implements IPurchaseOrderService {

    @Autowired
    ArticlesPurchaseService articlesPurchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    BatchService batchService;

    @Autowired
    AuthenticationService authenticationService;


    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {

        return PurchaseOrderDTO.builder()
                .id(purchaseOrder.getId())
                .date(purchaseOrder.getDate())
//                .clientId(purchaseOrder.getClient().getId())
                .orderStatus(purchaseOrder.getOrderStatus())
                .articlesPurchases(this.articlesPurchaseService.convertToDTO(purchaseOrder.getArticlesPurchases()))
                .totalPrice(purchaseOrder.getTotalPrice())
                .build();
    }

    public PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {
        return PurchaseOrder.builder()
//                .client(userRepository.findById(purchaseOrderDTO.getClientId()).orElse(null))
                .client(this.authenticationService.authenticated())
                .orderStatus(purchaseOrderDTO.getOrderStatus())
                .date(purchaseOrderDTO.getDate())
                .id(purchaseOrderDTO.getId())
                .articlesPurchases(this.articlesPurchaseService.convertToEntity(purchaseOrderDTO.getArticlesPurchases()))
                .build();
    }

    @Override
    public PurchaseOrder order(PurchaseOrder purchaseOrder){

        List<ArticlesPurchase> products = new ArrayList<>();

        for(ArticlesPurchase a: purchaseOrder.getArticlesPurchases()){

            Product p = this.productService.findById(a.getProduct().getId());

//            User client = (userRepository.findById(purchaseOrder.getClient().getId()).orElse(null));
            User client =this.authenticationService.authenticated();
            Boolean haveStock =
                    this.productService.verifyStock(p.getId(),a.getQuantity(),a.getBatchCode());
            Boolean lessThan3weak = this.productService.verifyIfDueDateLessThan3Weeks(p);

            if(haveStock && !lessThan3weak
////                    && client != null
            ){
                products.add(a);
                this.batchService.updateStock(p.getId(),a.getQuantity(),a.getBatchCode());
            }
        }
        System.out.println("PRODUCT : : " + products.get(0).getQuantity());
        System.out.println("TOTAL PRICE : : " + articlesPurchaseService.calcTotalPrice(products));
        purchaseOrder.setTotalPrice(this.articlesPurchaseService.calcTotalPrice(products));
        purchaseOrder.setArticlesPurchases(products);

        return this.purchaseOrderRepository.save(purchaseOrder);
    }


    public PurchaseOrder findById(Integer id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        PurchaseOrder purchaseOrderUpdated = purchaseOrderRepository.findById(purchaseOrder.getId()).orElse(null);
        purchaseOrderUpdated.setDate(purchaseOrder.getDate());
        purchaseOrderUpdated.setOrderStatus(purchaseOrder.getOrderStatus());
        purchaseOrderUpdated.setArticlesPurchases(purchaseOrder.getArticlesPurchases());
        purchaseOrderUpdated.setTotalPrice(purchaseOrder.getTotalPrice());

        return purchaseOrderRepository.saveAndFlush(purchaseOrderUpdated);

    }
}
