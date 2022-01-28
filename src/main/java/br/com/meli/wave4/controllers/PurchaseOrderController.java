package br.com.meli.wave4.controllers;

import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.TypeRefrigeration;
import br.com.meli.wave4.services.InboundOrderService;
import br.com.meli.wave4.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/")
public class PurchaseOrderController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProductList() {
        List<Product> productListPersistence = productService.findAll();
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productListPersistence);
    }

    @GetMapping("/list?query_type=product_category")
    public ResponseEntity<?> productListByCategory(@RequestParam TypeRefrigeration product_category) {
        List<Product> productListPersistence = productService.findAllByCategory(product_category);
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productListPersistence);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable Integer id) {
        return null;
    }

}
