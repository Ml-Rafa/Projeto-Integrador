package br.com.meli.wave4.controllers;

import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.services.ProductService;
import br.com.meli.wave4.services.PurchaseOrderService;
import br.com.meli.wave4.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/list")
    public ResponseEntity<?> getProductList(@RequestParam Integer productId, @RequestParam Integer warehouseId) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        Product product = productService.findById(productId);

        return ResponseEntity.ok(productService.filterProductInWarehouse(warehouse, product));
    }


}
