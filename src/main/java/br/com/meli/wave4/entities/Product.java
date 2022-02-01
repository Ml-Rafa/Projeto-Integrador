package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private BigDecimal price;

    @OneToMany(mappedBy = "product" )
    private List<Batch> batchList;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;



//    private Integer batchNumber;
//    id do produto está dentro do stock
//    private Integer quantityStock;


}
