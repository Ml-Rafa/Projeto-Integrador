package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseProductInfo {
    private Integer product;
    private Warehouse warehouse;
    private Integer quantity;

}
