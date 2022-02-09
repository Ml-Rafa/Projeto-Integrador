package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.*;
//import br.com.meli.wave4.repositories.ClientRepository;
import br.com.meli.wave4.exceptions.DueDateLessThan3WeeksException;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.repositories.PurchaseOrderRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                .client(this.authenticationService.authenticated())
                .orderStatus(purchaseOrderDTO.getOrderStatus())
                .date(purchaseOrderDTO.getDate())
                .id(purchaseOrderDTO.getId())
                .articlesPurchases(this.articlesPurchaseService.convertToEntity(purchaseOrderDTO.getArticlesPurchases()))
                .build();
    }

    @Override
    public PurchaseOrderDTO order(PurchaseOrder purchaseOrder){

        List<ArticlesPurchase> products = new ArrayList<>();
        List<String> mensagem = new ArrayList<>();

        PurchaseOrder purchaseOrderPersistence = PurchaseOrder.builder()
                .id(0)
                .date(null)
//                .clientId(purchaseOrder.getClient().getId())
                .orderStatus(null)
                .articlesPurchases(new ArrayList<>())
                .totalPrice(new BigDecimal(0))
                .build();

        for(ArticlesPurchase a: purchaseOrder.getArticlesPurchases()){

            Product p = this.productService.findById(a.getProductArticle().getId());

            User client =this.authenticationService.authenticated();
            try {
                Boolean haveStock =
                        this.productService.verifyStock(p.getId(),a.getQuantity(),a.getBatchCode());
                Boolean lessThan3weak = this.productService.verifyIfDueDateLessThan3Weeks(p);

                if(haveStock && !lessThan3weak){
                    products.add(a);
                    this.batchService.updateStock(p.getId(),a.getQuantity(),a.getBatchCode());
                }

            } catch (InsufficientStockException e) {
                mensagem.add("Quantidade do item " + p.getName() + " indisponível no momento");

            } catch (DueDateLessThan3WeeksException e) {
                mensagem.add("Produto fora da validade");

            }

        }

        purchaseOrder.setTotalPrice(this.articlesPurchaseService.calcTotalPrice(products));
        purchaseOrder.setArticlesPurchases(products);

        if(!products.isEmpty()) {
            purchaseOrderPersistence = this.purchaseOrderRepository.save(purchaseOrder);
            for(ArticlesPurchase a: purchaseOrderPersistence.getArticlesPurchases()){
                a.setPurchaseOrder(purchaseOrderPersistence);
                articlesPurchaseService.save(a);
            }
        }

        PurchaseOrderDTO purchaseOrderDTO = this.convertToDTO(purchaseOrderPersistence);
        purchaseOrderDTO.setMensagem(mensagem);

        return purchaseOrderDTO;
    }


    public PurchaseOrder findById(Integer id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        return purchaseOrder;
    }

    public PurchaseOrder update(PurchaseOrder purchaseOrder) {

        PurchaseOrder purchaseOrderUpdated = purchaseOrderRepository.findById(purchaseOrder.getId()).orElse(null);
        if(purchaseOrder.getOrderStatus().equals(OrderStatus.CANCELED)){
            assert purchaseOrderUpdated != null;
            return cancelPurchaseOrder(purchaseOrder, purchaseOrderUpdated);
        }

        List<ArticlesPurchase> products = new ArrayList<>();
        assert purchaseOrderUpdated != null;
        for(ArticlesPurchase a : purchaseOrderUpdated.getArticlesPurchases()) {
            Batch batch = batchService.findByBatchNumber(a.getBatchCode());
            for(ArticlesPurchase article : purchaseOrder.getArticlesPurchases()) {
                if (article.getId().equals(a.getId())) {
                    Boolean haveStock = this.productService.verifyStock(article.getProductArticle().getId(), article.getQuantity(), article.getBatchCode());
                    Boolean lessThan2Week = this.productService.verifyIfDueDateLessThan3Weeks(article.getProductArticle());
                    if (haveStock && !lessThan2Week) {
                        products.add(article);
                        purchaseOrder.setTotalPrice(articlesPurchaseService.calcTotalPrice(products));
                        purchaseOrderUpdated.setTotalPrice(purchaseOrder.getTotalPrice());
                        batchService.reverseStock(batch.getCurrentQuantity() + a.getQuantity() - article.getQuantity(), a.getBatchCode());
                    }
                }
            }
        }
        purchaseOrderUpdated.setDate(purchaseOrder.getDate());
        purchaseOrderUpdated.setOrderStatus(purchaseOrder.getOrderStatus());
        purchaseOrderUpdated.setArticlesPurchases(products);
        PurchaseOrder purchaseOrderPersistence = this.purchaseOrderRepository.saveAndFlush(purchaseOrderUpdated);
        for(ArticlesPurchase a: purchaseOrder.getArticlesPurchases()){
            a.setPurchaseOrder(purchaseOrderPersistence);
            articlesPurchaseService.save(a);
        }
        purchaseOrderPersistence.setTotalPrice(articlesPurchaseService.calcTotalPrice(products));
        return purchaseOrderPersistence;
    }

    private PurchaseOrder cancelPurchaseOrder(PurchaseOrder purchaseOrder, PurchaseOrder purchaseOrderUpdated) {
        assert purchaseOrderUpdated != null;
        for(ArticlesPurchase a : purchaseOrderUpdated.getArticlesPurchases()) {
            Batch batch = batchService.findByBatchNumber(a.getBatchCode());
            batchService.reverseStock(batch.getCurrentQuantity() + a.getQuantity(), a.getBatchCode());
        }
        purchaseOrder.setArticlesPurchases(new ArrayList<>());
        purchaseOrderUpdated.setArticlesPurchases(purchaseOrder.getArticlesPurchases());
        purchaseOrder.setTotalPrice(new BigDecimal(0));
        purchaseOrderUpdated.setTotalPrice(purchaseOrder.getTotalPrice());
        purchaseOrderUpdated.setDate(purchaseOrder.getDate());
        purchaseOrderUpdated.setOrderStatus(purchaseOrder.getOrderStatus());

        PurchaseOrder purchaseOrderPersistence = this.purchaseOrderRepository.saveAndFlush(purchaseOrderUpdated);
        for(ArticlesPurchase a: purchaseOrder.getArticlesPurchases()){
            a.setPurchaseOrder(purchaseOrderPersistence);
            articlesPurchaseService.save(a);
        }
        return purchaseOrderUpdated;
    }
}

















