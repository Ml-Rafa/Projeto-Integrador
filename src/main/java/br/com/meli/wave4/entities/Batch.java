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
    private Integer batchNumber = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product = new Product();
    @ManyToOne
    @JoinColumn(name = "inbound_order_id")
    private InboundOrder inboundOrder = new InboundOrder();

    @JsonBackReference
    @ManyToOne
    private Section section = new Section();

    @JsonBackReference
    @ManyToOne
    private User representative = new User();
//    @JsonBackReference
//    @ManyToOne
//    private Representative representative;

    private Double currentTemperature = 0.0;
    private Double minimumTemperature = 0.0;;
    private Integer initialQuantity = 0;
    private Integer currentQuantity = 0;
    private LocalDate manufacturingDate = LocalDate.now();
    private LocalDateTime manufacturingTime = LocalDateTime.now();
    private LocalDate dueDate = LocalDate.now();


}
