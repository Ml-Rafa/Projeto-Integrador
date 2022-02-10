package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.ArticlesPurchaseDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.services.ArticlesPurchaseService;
import br.com.meli.wave4.services.ProductService;
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


public class ArticlesPurchaseServiceTest {

    @InjectMocks
    ArticlesPurchaseService articlesPurchaseService;

    @Mock
    ProductService productService;

    List<ArticlesPurchase> articlesPurchaseList = new ArrayList<>();

    List<ArticlesPurchaseDTO> articlesPurchaseDTOS = new ArrayList<>();

    Product product;

    List<Batch> batchList = new ArrayList<>();

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);


        this.product=
                Product.builder()
                        .id(123)
                        .seller(new User())
                        .name("Pao de Queijo")
                        .dateValid( LocalDate.now().plusDays(21))
                        .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                        .price(new BigDecimal(20))
                        .batchList(this.batchList)
                        .build();

        this.articlesPurchaseList.add(
                ArticlesPurchase.builder()
                        .id(10)
                        .batchCode(20)
                        .productArticle(this.product)
                        .quantity(10)
                .build());

        this.articlesPurchaseDTOS.add(
                ArticlesPurchaseDTO.builder()
                        .product(10)
                        .batchCode(20)
                        .product(this.product.getId())
                        .build());
    }

    @Test
    public void convertToDTO(){

        assertInstanceOf(ArticlesPurchaseDTO.class,
                this.articlesPurchaseService.convertToDTO(this.articlesPurchaseList).get(0));

    }

    @Test
    public void calcTotalPrice(){
        assertEquals(new BigDecimal(200), this.articlesPurchaseService.calcTotalPrice(this.articlesPurchaseList));
    }

    @Test
    public void convertToEntity(){

        when(this.productService.findById(any())).thenReturn(this.product);
        assertInstanceOf(ArticlesPurchase.class,
                this.articlesPurchaseService.convertToEntity(this.articlesPurchaseDTOS).get(0));

    }


}
