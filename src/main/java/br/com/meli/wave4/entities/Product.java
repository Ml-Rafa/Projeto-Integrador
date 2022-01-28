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
    private String name;
    private TypeRefrigeration sectionTypeRefrigerated;
    private LocalDate dateValid;

//    @OneToOne(mappedBy = "product")
//    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

//    private Integer batchNumber;
//    id do produto está dentro do stock
//    private Integer quantityStock;


}
