package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.InboundOrderRepository;
import br.com.meli.wave4.services.InboundOrderService;
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


public class InboundOrderServiceTest {

    @InjectMocks
    InboundOrderService inboundOrderService;

    @Mock
    InboundOrderRepository inboundOrderRepository;

    InboundOrder inboundOrder;

    List<Batch> batchList = new ArrayList<>();

    Section section;

    Warehouse warehouse;

    Product product;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        this.warehouse = Warehouse.builder().id(10).build();

        this.section = Section
                .builder()
                .sectionCode(5)
                .availableCapacity(35.0)
                .warehouse(this.warehouse)
                .maxCapacity(35.0)
                .temperature(-5.0)
                .build();

        this.batchList.add(
                Batch.builder()
                        .section(this.section)
                        .initialQuantity(50)
                        .currentQuantity(50)
                        .build());

        this.product=
                Product.builder()
                        .id(123)
                        .seller(new Seller())
                        .name("Pao de Queijo")
                        .dateValid( LocalDate.now().plusDays(21))
                        .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                        .price(new BigDecimal(20))
                        .batchList(this.batchList)
                        .build();

        this.batchList.get(0).setProduct(this.product);
        this.section.setBatchList(this.batchList);

        this.inboundOrder = InboundOrder
                .builder()
                .orderNumber(1234)
                .warehouse(this.warehouse)
                .orderDate(LocalDate.now())
                .batchStock(this.batchList)
                .build();

    }


    @Test
    public void findById(){
        when((this.inboundOrderRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(this.inboundOrder));
        assertEquals(this.inboundOrder.getOrderNumber(), this.inboundOrderService.findById(any()).getOrderNumber());
    }

    @Test
    public void findByIdException(){
        when((this.inboundOrderRepository.findById(any()))).thenReturn(java.util.Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () ->  this.inboundOrderService.findById(any()).getOrderNumber());
    }

}
