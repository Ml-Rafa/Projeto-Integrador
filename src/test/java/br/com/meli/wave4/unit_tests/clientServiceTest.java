package br.com.meli.wave4.unit_tests;


import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class clientServiceTest {
    @InjectMocks
    ClientService clientService;

    @Mock
    UserRepository userRepository;

    User client;
    Product product;
    List<Batch> batchList = new ArrayList<>();
    List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    List<ArticlesPurchase> articlesPurchaseList = new ArrayList<>();

    @BeforeEach
    void setup() {
        this.product = Product.builder()
                .id(123)
                .name("Pao de Queijo")
                .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                .dateValid(LocalDate.now().plusDays(21))
                .price(new BigDecimal(20))
                .batchList(this.batchList)
                .seller(new User())
                .build();

        this.articlesPurchaseList.add(
                ArticlesPurchase.builder()
                        .id(10)
                        .batchCode(20)
                        .productArticle(this.product)
                        .quantity(10)
                        .build()
        );

        this.purchaseOrders.add(
                PurchaseOrder
                        .builder()
                        .id(20)
                        .client(User
                                .builder()
                                .id(10)
                                .name("Maria")
                                .document("44444")
                                .address("Rua 123")
                                .telephone("222222")
                                .build())
                        .orderStatus(OrderStatus.PAID)
                        .articlesPurchases(this.articlesPurchaseList)
                        .date(LocalDate.now())
                        .totalPrice(new BigDecimal(300))
                        .build()
        );

        this.client.builder()
                .id(10)
                .name("Maria")
                .document("44444")
                .address("Rua 123")
                .telephone("4809233232")
                .listPurchaseOrder(purchaseOrders)
                .build();
    }

    @Test
    public void findById() {
        when(this.userRepository.findById(any())).thenReturn(Optional.of(this.client));
        assertEquals(client, this.clientService.findById(any()));
    }
}
