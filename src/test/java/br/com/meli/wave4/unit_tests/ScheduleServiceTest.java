package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.repositories.*;
import br.com.meli.wave4.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    @Mock
    InboundOrderRepository inboundOrderRepository;
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    PurchaseOrderRepository purchaseOrderRepository;
    @Mock
    RepresentativeService representativeService;
    @Mock
    BatchService batchService;
    @Mock
    WarehouseService warehouseService;
    @Mock
    DeliveryTimeByStateInHoursRepository deliveryTimeByStateInHoursRepository;
    @Mock
    DeliveryDatesRepository deliveryDatesRepository;
    @Mock
    PurchaseOrderService purchaseOrderService;
    @Mock
    BatchRepository batchRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    WarehouseRepository warehouseRepository;

    InboundOrder inboundOrder;
    PurchaseOrder purchaseOrder;
    Batch batch;
    Batch batch2;
    List<DeliveryDates> deliveryDates = new ArrayList<>();
    DeliveryDates deliveryDate;
    DeliveryDates deliveryDate2;
    Schedule schedule;
    Schedule schedule2;
    ScheduleDTO scheduleDTO;
    List<Schedule> scheduleList = new ArrayList<>();
    Product product;
    List<ArticlesPurchase> articlesPurchaseList = new ArrayList<>();
    List<Batch> batchList = new ArrayList<>();
    List<InboundOrder> inboundOrderList = new ArrayList<>();
    List<Section> sectionList = new ArrayList<>();
    ScheduleService scheduleService;
    List<DeliveryTimeByStateInHours> deliveryTimeByStateInHours = new ArrayList<>();
    DeliveryTimeByStateInHours deliveryTimeByStateInHour;
    Section section;
    Warehouse warehouse;
    User client;
    User representative;
    User seller;
    ArticlesPurchase articlesPurchase;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.client = User
                .builder()
                .name("Gabriela da Rocha")
                .state("Santa Catarina")
                .id(10)
                .user("gabriela")
                .document("12345678")
                .address("Palhoça - SC")
                .active(true)
                .telephone("222222")
                .password("$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe")
                .document("3333333")
                .build();
        this.seller = User
                .builder()
                .name("Paula")
                .id(1)
                .document("3333433")
                .build();
        this.representative = User.builder()
                .name("José Rodriguez")
                .document("123.123.123")
                .warehouse(this.warehouse)
                .user("joserodriguez")
                .password("$2a$10$ACeicQVIBKhi6wodeFH3jO4ziB0pfQ3AVulvcsFL.32n3e3rFaiWe")
                .active(true)
                .build();


        this.inboundOrder = InboundOrder.builder()
                .orderDate(LocalDate.parse("2022-01-31"))
                .section(this.section)
                .warehouse(this.warehouse)
                .sellerId(1)
                .batchStock(this.batchList)
                .build();

        this.inboundOrderList.add(inboundOrder);

        this.product=
                Product.builder()
                        .id(2)
                        .name("Pao de Queijo Congelado")
                        .sectionTypeRefrigerated(TypeRefrigeration.FROZEN)
                        .dateValid(LocalDate.now().plusDays(21))
                        .price(new BigDecimal(10.0))
                        .articlesPurchases(articlesPurchaseList)
                        .seller(seller)
                        .build();

        this.product.setBatchList(this.batchList);

        this.deliveryTimeByStateInHours.add(
                DeliveryTimeByStateInHours.builder()
                        .id(1)
                        .hours(24)
                        .shippingValue(new BigDecimal("15.00"))
                        .state("Santa Catarina")
                        .warehouse(this.warehouse)
                        .build());

        this.deliveryTimeByStateInHour = DeliveryTimeByStateInHours.builder()
                .id(1)
                .hours(24)
                .shippingValue(new BigDecimal("15.00"))
                .state("Santa Catarina")
                .warehouse(this.warehouse)
                .build();

        this.scheduleDTO = ScheduleDTO.builder()
                .id(1)
                .deliveryDateTime(LocalDateTime.parse("2022-02-11T16:00:00"))
                .shippingValue(new BigDecimal("15.00"))
                .purchaseOrderId(1)
                .build();

        this.batch = Batch.builder()
                .batchNumber(1)
                .product(this.product)
                .initialQuantity(90)
                .currentQuantity(70)
                .currentTemperature(30.0)
                .minimumTemperature(5.0)
                .dueDate(LocalDate.parse("2024-01-31"))
                .manufacturingDate(LocalDate.parse("2022-01-31"))
                .manufacturingTime(LocalDateTime.parse("2022-01-31T14:51:16"))
                .representative(this.representative)
                .inboundOrder(this.inboundOrder)
                .build();
        this.section = Section.builder()
                .sectionCode(1)
                .availableCapacity(500.0)
                .maxCapacity(500.0)
                .storageType("FROZEN")
                .temperature(-5.0)
                .inboundOrderList(this.inboundOrderList)
                .batchList(this.batchList)
                .build();

        this.warehouse =  Warehouse.builder()
                .id(1)
                .geographicArea("São Paulo")
                .representative(this.representative)
                .build();

        this.section.setWarehouse(this.warehouse);
        this.batch.setSection(this.section);

        this.deliveryDates = Arrays.asList(
                DeliveryDates.builder()
                        .id(21)
                        .deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.parse("2022-02-11T16:00:00"))
                        .dateIsAvailable(true)
                        .build(),
                DeliveryDates.builder()
                        .id(22)
                        .deliveryLocation("Santa Catarina")
                        .dateTime(LocalDateTime.parse("2022-02-12T17:00:00"))
                        .dateIsAvailable(true)
                        .build()
        );

        this.purchaseOrder = PurchaseOrder
                .builder()
                .id(1)
                .client(User
                        .builder()
                        .name("Gabriela da Rocha")
                        .state("Santa Catarina")
                        .id(10)
                        .user("gabriela")
                        .document("12345678")
                        .address("Palhoça - SC")
                        .active(true)
                        .telephone("222222")
                        .password("$2a$10$fjYcodWEp6l5sWF9wAF7cu1tQryOmHck.32sD1fG4H/hpaOP3H6oe")
                        .document("3333333")
                        .build())
                .orderStatus(OrderStatus.WAITING_PAYMENT)
                .date(LocalDate.parse("2022-01-25"))
                .totalPrice(new BigDecimal(500))
                .build();

        this.articlesPurchase = ArticlesPurchase.builder()
                .id(1)
                .batchCode(1)
                .productArticle(this.product)
                .quantity(20)
                .purchaseOrder(this.purchaseOrder)
                .build();
        this.articlesPurchase.setPurchaseOrder(this.purchaseOrder);
        this.purchaseOrder.setArticlesPurchases(Arrays.asList(
                this.articlesPurchase
        ));

        this.schedule = Schedule.builder()
                .id(1)
                .deliveryDateTime(LocalDateTime.parse("2022-02-12T17:00:00"))
                .shippingValue(new BigDecimal("15.00"))
                .build();

        this.schedule2 = Schedule.builder()
                .id(1)
                .deliveryDateTime(LocalDateTime.parse("2022-02-12T16:00:00"))
                .shippingValue(new BigDecimal("15.00"))
                .build();

        this.scheduleList.addAll(Arrays.asList(this.schedule, this.schedule2));
        this.purchaseOrder.setId(1);
        this.purchaseOrder.setSchedule(this.schedule);
        this.schedule.setPurchaseOrder(this.purchaseOrder);


        this.scheduleService = new ScheduleService(
                this.scheduleRepository,
                this.deliveryTimeByStateInHoursRepository,
                this.deliveryDatesRepository,
                this.purchaseOrderRepository,
                this.purchaseOrderService,
                this.batchService,
                this.batchRepository,
                this.productRepository,
                this.representativeService,
                this.userRepository,
                this.warehouseRepository,
                this.warehouseService
        );
    }

    @Test
    public void findById() {
        when(this.scheduleRepository.findById(any())).thenReturn(Optional.of(this.schedule));
        assertEquals(1, this.scheduleService.getById(any()).getId());
    }

    @Test
    public void findAll() {
        when(this.scheduleRepository.findAll()).thenReturn(scheduleList);
        assertEquals(1, this.scheduleService.findAll().get(0).getId());
    }

    @Test
    public void shouldRegisterASchedule() throws IOException {

        when(this.purchaseOrderService.findById(any())).thenReturn(this.purchaseOrder);
        when(this.batchService.findByBatchNumber(any())).thenReturn(this.batch);
        when(this.deliveryTimeByStateInHoursRepository.findByStateAndWarehouse(any(), any())).thenReturn(this.deliveryTimeByStateInHour);
        when(this.deliveryDatesRepository.findAllByDeliveryLocationAndDateIsAvailable(any(), any())).thenReturn(this.deliveryDates);
        when(this.scheduleRepository.save(this.schedule)).thenReturn(this.schedule);

        Schedule scheduleRegister = this.scheduleService.registerSchedule(this.schedule);

        assertEquals(this.schedule.getId(), scheduleRegister.getId());
    }

    @Test
    void shouldReturnScheduleDTO(){
        assertInstanceOf(ScheduleDTO.class, scheduleService.convertToDTO(this.schedule));
    }

    @Test
    public void shouldReturnScheduleEntity(){
        when(this.purchaseOrderRepository.findById(any())).thenReturn(Optional.of(this.purchaseOrder));
        assertInstanceOf(Schedule.class, this.scheduleService.convertToEntity(this.scheduleDTO));
    }

    @Test
    void ShouldReturnDeliveryDatesList(){

        when(this.purchaseOrderService.findById(any())).thenReturn(this.purchaseOrder);
        when(this.batchService.findByBatchNumber(any())).thenReturn(batch);
        when(deliveryTimeByStateInHoursRepository.findByStateAndWarehouse(any(), any())).thenReturn(this.deliveryTimeByStateInHour);
        when(deliveryDatesRepository.findAllByDeliveryLocationAndDateIsAvailable(any(), any())).thenReturn(deliveryDates);

        assertEquals(1, this.scheduleService.getAvailableDates(this.purchaseOrder.getId()).size());
    }

}


