package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Batch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batchNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;
    @ManyToOne
    @JoinColumn(name = "inbound_order_id")
    @JsonIgnore
    private InboundOrder inboundOrder;

    @JsonBackReference
    @ManyToOne
    private Section section;

    @JsonBackReference
    @ManyToOne
    private User representative;
//    @JsonBackReference
//    @ManyToOne
//    private Representative representative;

    private Double currentTemperature;
    private Double minimumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Integer discountOfDueDate;

}
