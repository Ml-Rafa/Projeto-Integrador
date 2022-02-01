package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.entities.TypeRefrigeration;
import br.com.meli.wave4.services.ProductService;
import br.com.meli.wave4.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/")
public class PurchaseOrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<?> getProductList() {
        List<Product> productListPersistence = productService.getAll();
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productListPersistence);
    }

    @GetMapping("/list?category=product_category")
    public ResponseEntity<?> productListByCategory(@RequestParam TypeRefrigeration product_category) {
        List<Product> productListPersistence = productService.findAllByCategory(product_category);
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productListPersistence);
    }

    @PostMapping("/orders/")
    public ResponseEntity<?> registerPurchaseOrder(PurchaseOrderDTO purchaseOrderDto, UriComponentsBuilder uriBuilder) {
        PurchaseOrder purchaseOrder = this.purchaseOrderService.convertToEntity(purchaseOrderDto);

        //chamada do método que vai salvar o carrinho de compras
        // purchaseOrderService.create(purchaseOrder);

        return ResponseEntity.created(uriBuilder
            .path("register-purchase-order")
            .buildAndExpand("register")
            .toUri()).body(purchaseOrderService.convertToDTO(purchaseOrder));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> productsOnOrder(PurchaseOrder purchaseOrder) {
        return null;
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable Integer id) {
        return null;
    }

    @PostMapping("fresh-products/orders")
    public ResponseEntity<?> order(@RequestBody PurchaseOrderDTO purchaseOrderDTO, UriComponentsBuilder uriBuilder){

        PurchaseOrder purchaseOrder = this.purchaseOrderService.convertToEntity(purchaseOrderDTO);

        return ResponseEntity.created(uriBuilder
                .path("register-purchase-order")
                .buildAndExpand("register")
                .toUri()).body(this.purchaseOrderService.order(purchaseOrder));

    }

}
