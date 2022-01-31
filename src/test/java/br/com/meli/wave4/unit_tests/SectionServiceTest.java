package br.com.meli.wave4.unit_tests;
import static org.mockito.Mockito.*;

import br.com.meli.wave4.entities.*;
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
                .thenReturn(java.util.Optional.ofNullable(this.section));
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


//    @Override
//    public boolean verifySection(Integer sectionCode, Integer warehouseId) {
//        Section sectionPersistence = this.findBySectionCode(sectionCode);
//
//        if (sectionPersistence != null || sectionPersistence.getWarehouse().getId().equals(warehouseId)) {
//            return true;
//        }
//        throw new InvalidSectionException();
//    }
}
