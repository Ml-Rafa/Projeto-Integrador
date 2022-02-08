package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.repositories.PurchaseOrderRepository;
import br.com.meli.wave4.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class PurchaseOrderServiceTest {


    @InjectMocks
    PurchaseOrderService purchaseOrderService;

    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    ProductService productService;

    @Mock
    BatchService batchService;

    @Mock
    ArticlesPurchaseService articlesPurchaseService;

    PurchaseOrder purchaseOrder;

    PurchaseOrderDTO purchaseOrderDTO;

    Product product;

    List<ArticlesPurchase> articlesPurchaseList = new ArrayList<>();

    List<Batch> batchList = new ArrayList<>();

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);


        this.product=
                Product.builder()
                        .id(123)
                        .name("Pao de Queijo")
                        .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                        .dateValid( LocalDate.now().plusDays(21))
                        .price(new BigDecimal(20))
                        .batchList(this.batchList)
                        .seller(new User())
                        .build();

        this.articlesPurchaseList.add(
                ArticlesPurchase.builder()
                        .id(10)
                        .batchCode(20)
                        .product(this.product)
                        .quantity(10)
                        .build());


        this.purchaseOrder = PurchaseOrder
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
                .build();

        this.purchaseOrderDTO =
                PurchaseOrderDTO
                        .builder()
                        .id(20)
                        .orderStatus(OrderStatus.PAID)
                        .articlesPurchases(new ArrayList<>())
                        .date(LocalDate.now())
                        .totalPrice(new BigDecimal(300))
                        .build();


    }

   // @Test
   // public void update(){

  //      when(this.productService.findById(any())).thenReturn(this.product);
    //    when(this.productService.verifyStock(any(),any(),any())).thenReturn(true);
    //    when(this.productService.verifyIfDueDateLessThan3Weeks(any())).thenReturn(true);

     //   this.purchaseOrderService.update(this.purchaseOrder);
  //  }

    @Test
    public void convertToDTO(){
        assertInstanceOf(PurchaseOrderDTO.class, this.purchaseOrderService.convertToDTO(this.purchaseOrder));
    }

    @Test
    public void findById(){
        when(this.purchaseOrderRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.purchaseOrder));
        assertEquals(this.purchaseOrder.getId(), this.purchaseOrderService.findById(any()).getId());
    }

    @Test
    public void convertToEntity(){
        when(this.authenticationService.authenticated()).thenReturn(new User());
        when(this.articlesPurchaseService.convertToEntity(any())).thenReturn(this.articlesPurchaseList);
        assertNotNull(this.purchaseOrderService.convertToEntity(this.purchaseOrderDTO));
    }
}
