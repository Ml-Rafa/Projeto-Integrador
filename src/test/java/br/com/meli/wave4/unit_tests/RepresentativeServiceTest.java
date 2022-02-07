package br.com.meli.wave4.unit_tests;


import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.RepresentativeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class RepresentativeServiceTest {

    @InjectMocks
    RepresentativeService representativeService;

    @Mock
    UserRepository userRepository;

    User representative;

    Warehouse warehouse;

    List<Batch> batchList = new ArrayList<>();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.warehouse =
                Warehouse.builder().build();

        this.representative =

                new User();
                this.representative.setId(3);
                this.representative.setWarehouse(this.warehouse);
                this.representative.setBatch(this.batchList);

    }

    @Test
    public void findById() {

        when(this.userRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(this.representative));
        assertEquals(3,this.representativeService.findById(any()).getId());

    }


}
