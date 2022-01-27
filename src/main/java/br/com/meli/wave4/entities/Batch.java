package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Double currentTemperature;
    private Double minimumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
}