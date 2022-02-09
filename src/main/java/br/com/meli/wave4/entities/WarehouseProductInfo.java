package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseProductInfo {
    private Integer product;
    private Warehouse warehouse;
    private Integer quantity;

}
