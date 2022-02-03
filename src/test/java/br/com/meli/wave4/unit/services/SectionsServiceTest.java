package br.com.meli.wave4.unit.services;

import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.InvalidSectionException;
import br.com.meli.wave4.repositories.SectionRepository;
import br.com.meli.wave4.services.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SectionsServiceTest {

    @InjectMocks
    SectionService sectionService;

    @Mock
    SectionRepository sectionRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnTrue(){

        Warehouse warehouse =
                Warehouse.builder()
                        .id(10).build();

        Section section =
                Section.builder()
                .warehouse(warehouse).build();

        when(sectionRepository.findBySectionCode(any())).thenReturn(java.util.Optional.ofNullable(section));

        assertTrue(this.sectionService.verifySection(any(),10));

    }

    @Test
    public void shouldThrowInvalidSectionException (){

        Warehouse warehouse =
                Warehouse.builder()
                        .id(10).build();

        Section section =
                Section.builder()
                        .warehouse(warehouse).build();

        when(sectionRepository.findBySectionCode(any())).thenReturn(java.util.Optional.ofNullable(section));

        Assertions.assertThrows(
                InvalidSectionException.class,
                ()->{
                    this.sectionService.verifySection(any(),1);
                }
        );




    }


    @Test


}
