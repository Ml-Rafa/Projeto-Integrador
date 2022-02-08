package br.com.meli.wave4.unit_tests;

import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.InvalidWarehouseException;
import br.com.meli.wave4.repositories.WarehouseRepository;
import br.com.meli.wave4.services.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class WarehouseServiceTest {

    @InjectMocks
    WarehouseService warehouseService;

    @Mock
    WarehouseRepository warehouseRepository;

    Warehouse warehouse;

    Set<Section> sectionList = new HashSet<>();

    User representative;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.sectionList.add(
                Section.builder()
                        .sectionCode(5)
                        .availableCapacity(35.0)
                        .warehouse(this.warehouse)
                        .maxCapacity(35.0)
                        .temperature(-5.0)
                        .build());

        this.representative = User.builder().id(1).build();

        this.warehouse = Warehouse.builder()
                .id(123)
                .geographicArea("testes")
                .representative(new User())
                .sectionSet(this.sectionList)
                .build();
    }

    @Test
    public void findByIdTrue() {
        when(this.warehouseRepository.findById(any())).thenReturn(Optional.of(this.warehouse));
        assertEquals(warehouse, this.warehouseService.findById(any()));
    }

    @Test
    public void findAll(){
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(this.warehouse);
        when(this.warehouseRepository.findAll()).thenReturn(warehouses);
        assertEquals(warehouses.get(0).getId(), this.warehouseService.findAll().get(0).getId());
    }

    @Test
    public void verifyWarehouseThrowException() {
        when(this.warehouseRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        assertThrows(InvalidWarehouseException.class, () -> this.warehouseService.verifyWarehouse(any()));
    }

    @Test
    public void verifyWarehouseTrue() {
        when(this.warehouseRepository.findById(any())).thenReturn(Optional.of(this.warehouse));
        assertEquals(true, this.warehouseService.verifyWarehouse(any()));
    }

}
