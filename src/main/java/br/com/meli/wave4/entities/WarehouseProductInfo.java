package br.com.meli.wave4.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseProductInfo {
    private Integer product;
    private Warehouse warehouse;
    private Integer quantity;

}
