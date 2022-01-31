package br.com.meli.wave4.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "stock")
    private Warehouse warehouse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "representative_id", referencedColumnName = "id")
    private Representative representative;

    @OneToOne(mappedBy = "stock")
    private Product product;
    private Integer quantity;
    private LocalDate insertionDate;

    public Stock(Warehouse warehouse, Representative representative, Product product, Integer quantity, LocalDate insertionDate) {
        this.warehouse = warehouse;
        this.representative = representative;
        this.product = product;
        this.quantity = quantity;
        this.insertionDate = insertionDate;
    }
}
