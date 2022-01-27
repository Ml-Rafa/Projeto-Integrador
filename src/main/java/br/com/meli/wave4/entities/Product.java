package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TypeRefrigeration sectionTypeRefrigerated;
    private LocalDate dateValid;

    @OneToOne(mappedBy = "product")
    private Batch batch;
    private Integer lotNumber;
    private Integer quantityStock;


}
