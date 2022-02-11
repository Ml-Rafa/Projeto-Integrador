package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "delivery_times")
public class DeliveryTimeByStateInHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String state;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    private Integer hours;
    @Column(name = "shipping_value")
    private BigDecimal shippingValue;
}
