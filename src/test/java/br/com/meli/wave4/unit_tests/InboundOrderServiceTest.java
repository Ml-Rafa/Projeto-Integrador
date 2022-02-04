package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.BatchDTO;
import br.com.meli.wave4.DTO.InboundOrderDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.exceptions.RepresentativeNotCorrespondentException;
import br.com.meli.wave4.repositories.InboundOrderRepository;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.services.BatchService;
import br.com.meli.wave4.services.InboundOrderService;
import br.com.meli.wave4.services.RepresentativeService;
import br.com.meli.wave4.services.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


public class InboundOrderServiceTest {

    @InjectMocks
    InboundOrderService inboundOrderService;

    @Mock
    InboundOrderRepository inboundOrderRepository;

    @Mock
    SectionRepository sectionRepository;

    @Mock
    RepresentativeService representativeService;

    @Mock
    BatchService batchService;

    @Mock
    SectionService sectionService;

    InboundOrder inboundOrder;

    InboundOrderDTO inboundOrderDTO;

    List<Batch> batchList = new ArrayList<>();

    List<BatchDTO> batchDTOList = new ArrayList<>();

    Section section;

    Warehouse warehouse;

    Product product;

    Representative representative;

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
                .section(this.section)
                .build();

        this.batchDTOList.add(
                BatchDTO.builder()
                        .batchNumber(123)
                        .productId(30)
                        .initialQuantity(25)
                        .currentQuantity(20)
                        .currentTemperature(22.5)
                        .minimumTemperature(15.0)
                        .dueDate(LocalDate.now())
                        .inboundOrderId(1)
                        .representativeId(10)
                        .dueDate(LocalDate.now().plusDays(22))
                        .manufacturingDate(LocalDate.now().minusDays(10))
                        .manufacturingTime(LocalDateTime.now().minusDays(10))
                        .build());

        this.inboundOrderDTO =
                InboundOrderDTO.builder()
                        .orderNumber(123)
                        .orderDate(LocalDate.now())
                        .sectionCode(1)
                        .warehouseCode(1)
                        .batchStock(this.batchDTOList)
                .build();

        representative = new Representative();
        representative.setId(77);
        representative.setBatch(this.batchList);
        representative.setWarehouse(this.warehouse);
        representative.setName("Joao");
        representative.setDocument("777777");

        this.batchList.get(0).setRepresentative(representative);

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

    @Test
    public void convertToEntity(){
        when(this.sectionRepository.findBySectionCode(any())).thenReturn(java.util.Optional.ofNullable(this.section));
        assertInstanceOf(InboundOrder.class, this.inboundOrderService.convertToEntity(this.inboundOrderDTO));
    }

    @Test
    public void getRepresentative(){

        when(this.representativeService.findById(any())).thenReturn(representative);
        assertEquals("Joao", this.inboundOrderService.getRepresentative(this.batchList.get(0)).getName());

        when(this.representativeService.findById(any())).thenReturn(null);
        assertThrows(RepresentativeNotCorrespondentException.class,
                () -> this.inboundOrderService.getRepresentative(this.batchList.get(0)));

    }

    @Test
    public void getSection(){
        when(this.sectionService.findBySectionCode(any())).thenReturn(this.section);
        assertEquals(this.section.getSectionCode(),
                this.inboundOrderService.getSection(this.inboundOrder).getSectionCode());

        when(this.sectionService.findBySectionCode(any())).thenReturn(null);
        assertThrows(InvalidSectionException.class, ()->this.inboundOrderService.getSection(this.inboundOrder));
    }

}
