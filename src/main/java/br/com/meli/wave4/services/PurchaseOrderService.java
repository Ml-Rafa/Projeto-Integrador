package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.entities.Client;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.repositories.ClientRepository;
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
    ClientRepository clientRepository;


    @Override
    public PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDTO.builder()
                .totalPrice(articlesPurchaseService.calcTotalPrice(purchaseOrder.getArticlesPurchases()))
                .build();
    }

    public PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {

        return PurchaseOrder.builder()
               .client(this.clientRepository.getById(purchaseOrderDTO.getClientId()))
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

            Client client =  new Client();//this.clientRepository.getById(purchaseOrder.getClient().getId());
            Boolean haveStock =
                    this.productService.verifyStock(p.getId(),a.getQuantity(),1);
            Boolean lessthan3weeak = this.productService.verifyIfDueDateLessThan3Weeks(p);

            if(haveStock && lessthan3weeak && client != null ){
                products.add(a);
                this.productService.updateStock(p.getId(),a.getQuantity(),1);
            }
        }

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(purchaseOrder.getId())
                .orderStatus(purchaseOrder.getOrderStatus())
                .clientId(purchaseOrder.getId())
                .date(purchaseOrder.getDate())
                .articlesPurchases(this.articlesPurchaseService.convertToDTO(products))
                .totalPrice(this.articlesPurchaseService.calcTotalPrice(products))
                .build();

        return purchaseOrderDTO;
    }
}
