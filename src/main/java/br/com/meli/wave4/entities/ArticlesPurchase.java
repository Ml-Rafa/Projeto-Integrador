package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class ArticlesPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productArticle;
    private Integer quantity;
    private Integer batchCode;
    @ManyToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    @JsonIgnore
    private PurchaseOrder purchaseOrder;
}