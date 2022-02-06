package br.com.meli.wave4.unit_tests;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.services.SectionService;
import br.com.meli.wave4.services.iservices.ISectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;

public class SectionServiceTest {

    SectionRepository sectionRepositoryMock = mock(SectionRepository.class);
    Section section = null;
    Section section2 = null;
    Warehouse warehouse = null;
    Batch batch = null;
    Batch batch2 = null;
    Product product = null;
    Product product2 = null;

    SectionService sectionService = null;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.product = Product.builder()
                .id(1)
                .name("Requeijão")
                .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                .price(new BigDecimal("16.00"))
                .build();
        this.product2 = Product.builder()
                .id(2)
                .name("Manteiga")
                .sectionTypeRefrigerated(TypeRefrigeration.REFRIGERATED)
                .price(new BigDecimal("11.00"))
                .build();

        this.warehouse = Warehouse.builder().geographicArea("São Paulo").build();

        this.batch = Batch.builder()
                .batchNumber(1)
                .currentQuantity(130)
                .initialQuantity(150).product(product).build();
        this.batch2 = Batch.builder()
                .batchNumber(2)
                .currentQuantity(63)
                .initialQuantity(200).product(product).build();
        this.section = Section.builder()
                .sectionCode(1)
                .availableCapacity(200.0)
                .warehouse(warehouse)
                .build();
        this.section2 = Section.builder()
                .sectionCode(2)
                .availableCapacity(300.0)
                .batchList(Arrays.asList(batch, batch2))
                .warehouse(Warehouse.builder().geographicArea("Minas Gerais").build())
                .build();

        this.sectionService = new SectionService(this.sectionRepositoryMock);

    }

    @Test
    void shouldReturnSectionByCode(){
        when(this.sectionRepositoryMock.findBySectionCode(1))
                .thenReturn(this.section);
        Assertions.assertEquals(
                "São Paulo",
                this.sectionService.findBySectionCode(1).getWarehouse().getGeographicArea()
        );
    }

    @Test
    void shouldReturnTotalQuantityProductsInSection(){
        ISectionService sectionServiceMock = mock(ISectionService.class);
        Assertions.assertEquals(
                193,
                this.sectionService.getTotalProductsInSection(this.section2)
        );
    }

    @Test
    public void shouldReturnTrue(){

        Warehouse warehouse = Warehouse.builder().id(10).build();

        Section section = Section.builder().warehouse(warehouse).build();

        when(sectionRepositoryMock.findBySectionCode(any())).thenReturn(section);

        assertTrue(this.sectionService.verifySection(any(),10));

    }

    @Test
    public void shouldThrowInvalidSectionException (){

        Warehouse warehouse = Warehouse.builder().id(10).build();

        Section section = Section.builder().warehouse(warehouse).build();

        when(sectionRepositoryMock.findBySectionCode(any())).thenReturn(section);

        assertThrows(
                InvalidSectionException.class, ()->{
                    this.sectionService.verifySection(any(),1);
                }
        );
    }


    @Test
    public void verifyBatchInSectionTest(){

        Section section = Section.builder().sectionCode(10).build();

        Batch batch = Batch.builder().section( Section.builder().sectionCode(10).build()).build();

        assertTrue(this.sectionService.verifyBatchInSection(batch,section));

    }

    @Test
    public void verifyBatchInSectionThrowException(){

        Section section = Section.builder().sectionCode(10).build();

        Batch batch = Batch.builder().section( Section.builder().sectionCode(9).build()).build();

        assertThrows(
                InvalidSectionException.class, ()->{
                    this.sectionService.verifyBatchInSection(batch,section);
                });
    }




}
