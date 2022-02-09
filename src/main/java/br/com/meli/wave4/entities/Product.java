package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TypeRefrigeration sectionTypeRefrigerated;
    private LocalDate dateValid;
    private BigDecimal price;

//    @OneToMany(mappedBy = "product" )
//    private List<Batch> batchList = new ArrayList<>();

    @OneToMany(mappedBy = "product" )
    private List<Batch> batchList = new ArrayList<>();

    @OneToMany(mappedBy = "productArticle")
    private List<ArticlesPurchase> articlesPurchases = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "seller_id", nullable = false)
//    private Seller seller;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;



//    private Integer batchNumber;
//    id do produto está dentro do stock
//    private Integer quantityStock;



}
