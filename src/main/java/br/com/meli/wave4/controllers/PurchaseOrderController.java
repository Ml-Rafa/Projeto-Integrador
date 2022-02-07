package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.ProductDTO;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class PurchaseOrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<?> getProductList() {

        List<Product> productListPersistence = productService.getAll();
        List<ProductDTO> productDTOList = productListPersistence.stream().map(productService::convertToDTO).collect(Collectors.toList());
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/list")
    public ResponseEntity<?> productListByCategory(@RequestParam String category) {
        String type = "";
        switch (category.toUpperCase(Locale.ROOT)){
            case "FS": type = "FRESH";
            break;
            case "RF": type = "REFRIGERATED";
                break;
            case "FF": type = "FROZEN";
                break;
            default: return ResponseEntity.badRequest().body("Parâmetro informado é inválido.\n" +
                    "Parâmetros válidos:\n" +
                    "FS : Fresco;\n" +
                    "RF : Refrigerado;\n" +
                    "FF : Congelado");
        }
        List<ProductDTO> productListPersistence = productService.findAllByCategory(TypeRefrigeration.valueOf(type))
                .stream().map(productService::convertToDTO)
                .collect(Collectors.toList());
        return productListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(productListPersistence);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> productsOnOrder(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(purchaseOrderService.findById(id));
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<?> updatePurchaseOrder(@PathVariable Integer id, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        purchaseOrderDTO.setId(id);

        PurchaseOrder entity = this.purchaseOrderService.convertToEntity(purchaseOrderDTO);
        purchaseOrderService.update(entity);

        return ResponseEntity.status(200).body(purchaseOrderService.convertToDTO(entity));
    }

    @PostMapping("/orders")
    public ResponseEntity<?> order(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO, UriComponentsBuilder uriBuilder){

        PurchaseOrder purchaseOrder = this.purchaseOrderService.convertToEntity(purchaseOrderDTO);

        return ResponseEntity.created(uriBuilder
                .path("register-purchase-order")
                .buildAndExpand("register")
                .toUri()).body(purchaseOrderService.convertToDTO(this.purchaseOrderService.order(purchaseOrder)));
    }
}
