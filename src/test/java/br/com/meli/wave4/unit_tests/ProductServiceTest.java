package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.DTO.BatchSimpleResponseDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.DueDateLessThan3WeeksException;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.repositories.ProductRepository;
import br.com.meli.wave4.services.ProductService;
import br.com.meli.wave4.services.WarehouseService;
import br.com.meli.wave4.services.iservices.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class ProductServiceTest {


    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    WarehouseService warehouseService;

    Product product;

    List<Batch> batchList = new ArrayList<>();

    Section section;

    Warehouse warehouse;

    @BeforeEach
    void setup() {
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
    }


    @Test
    public void verifyStockTrue(){

        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.product));

        assertTrue(this.productService.verifyStock(123,10,5));

    }

    @Test
    public void verifyStockFalse(){

        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.product));

        assertThrows(InsufficientStockException.class,
                ()-> this.productService.verifyStock(123,100,5));

    }

    @Test
    public void verifyIfDueDateLessThan3WeeksThrowException (){

        this.product.setDateValid(LocalDate.now().minusDays(21));
        assertThrows(DueDateLessThan3WeeksException.class,
                ()-> this.productService.verifyIfDueDateLessThan3Weeks(this.product));
    }

    @Test
    public void verifyIfDueDateLessThan3WeeksFalse(){
        assertFalse(this.productService.verifyIfDueDateLessThan3Weeks(this.product));
    }

    @Test
    public void findByIdTrue(){
        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.product));
        assertEquals(product,this.productService.findById(any()));
    }

    @Test
    public void findByIdThrowException(){
        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));
        assertThrows(NotFoundException.class, ()-> this.productService.findById(any()));

    }

    @Test
    public void getAll(){

        List<Product> productList = new ArrayList<>();
        productList.add(this.product);

        when(this.productRepository.findAll()).thenReturn(productList);

        assertEquals(productList.get(0).getId(),this.productService.getAll().get(0).getId());
    }

    @Test
    public void save(){
        when(this.productRepository.save(any())).thenReturn(this.product);
        assertTrue(this.product.equals(this.productService.save(any())));
    }

    @Test
    public void findAllByCategory(){
        when(this.productRepository.findAllBySectionTypeRefrigerated(any())).thenReturn(Arrays.asList(this.product));
        assertEquals(this.product,this.productService.findAllByCategory(any()).get(0));
    }

    @Test
    public void update(){

        Product p = Product.builder().id(1234).build();

        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.product));
        when(this.productRepository.saveAndFlush(any())).thenReturn(p);

        assertEquals(p.getId(),this.productService.update(this.product).getId());

        when(this.productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(null));
        assertThrows(AssertionError.class,()->this.productService.update(this.product).getId());


    }

    @Test
    public void orderByCurrentQuantity(){
        List<BatchSimpleResponseDTO> batchSimpleResponseDTOS = new LinkedList<>();
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().batchNumber(123).currentQuantity(10).build());
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().batchNumber(321).currentQuantity(8).build());

        assertEquals(8,
                this.productService.orderByCurrentQuantity(batchSimpleResponseDTOS).get(0).getCurrentQuantity());
        assertEquals(10,
                this.productService.orderByCurrentQuantity(batchSimpleResponseDTOS).get(1).getCurrentQuantity());
    }

    @Test
    public void orderByDueDate(){

        List<BatchSimpleResponseDTO> batchSimpleResponseDTOS = new LinkedList<>();
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().dueDate(LocalDate.now().plusDays(10)).build());
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().dueDate(LocalDate.now()).build());

        assertEquals(LocalDate.now(),
                this.productService.orderByDueDate(batchSimpleResponseDTOS).get(0).getDueDate());
        assertEquals(LocalDate.now().plusDays(10),
                this.productService.orderByDueDate(batchSimpleResponseDTOS).get(1).getDueDate());

    }

    @Test
    public void orderByBatchNumber(){
        List<BatchSimpleResponseDTO> batchSimpleResponseDTOS = new LinkedList<>();
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().batchNumber(321).build());
        batchSimpleResponseDTOS.add(BatchSimpleResponseDTO.builder().batchNumber(123).build());

        assertEquals(123,
                this.productService.orderByBatchNumber(batchSimpleResponseDTOS).get(0).getBatchNumber());
        assertEquals(321,
                this.productService.orderByBatchNumber(batchSimpleResponseDTOS).get(1).getBatchNumber());

    }

    @Test
    public void getBatchSimpleResponseDTOS(){

        List<Batch> batchList = new LinkedList<>();

        batchList.add(Batch.builder().batchNumber(123).currentQuantity(50).dueDate(LocalDate.now()).build());
        batchList.add( Batch.builder().batchNumber(124).currentQuantity(20).dueDate(LocalDate.now().plusDays(1)).build());

        assertInstanceOf(BatchSimpleResponseDTO.class, this.productService.getBatchSimpleResponseDTOS(batchList).get(0));

    }

    @Test
    public void getBatchListInSpecificWarehouse(){
        assertNotNull(this.productService.getBatchListInSpecificWarehouse(this.warehouse,this.product));
    }

    @Test
    public void countProductInWarehouse(){

        List<Warehouse>  warehouseList = new ArrayList<>();
        warehouseList.add(
                Warehouse.builder()
                        .id(9)
                        .sectionSet(Set.of(this.section))
                        .build());
        warehouseList.add(
                Warehouse.builder()
                        .id(7)
                        .sectionSet(Set.of(this.section))
                        .build());

        when(this.warehouseService.findAll()).thenReturn(warehouseList);

        assertNotNull(this.productService.countProductInWarehouse(123));
        assertThrows(NotFoundException.class,()->this.productService.countProductInWarehouse(432));

    }

    @Test
    public void filterProductInWarehouse(){

        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'l'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'L'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'c'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'C'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'F'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'F'));
        assertNotNull(this.productService.filterProductInWarehouse(this.warehouse,this.product,'A'));


    }

}
