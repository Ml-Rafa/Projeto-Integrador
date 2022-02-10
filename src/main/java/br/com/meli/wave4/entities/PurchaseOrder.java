package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private LocalDate date;
//    private Integer clientId;

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<ArticlesPurchase> articlesPurchases = new ArrayList<>();

    private BigDecimal totalPrice;

    @OneToOne(mappedBy = "purchaseOrder")
    private Schedule schedule;
}
