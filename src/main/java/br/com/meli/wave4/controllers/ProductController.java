package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.ProductNearExpireDateDTO;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.ProductNearExpireDate;
import br.com.meli.wave4.entities.Section;
import br.com.meli.wave4.entities.Warehouse;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.services.ProductService;
import br.com.meli.wave4.services.PurchaseOrderService;
import br.com.meli.wave4.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("/list/{productId}/{warehouseId}")
    public ResponseEntity<?> getProductList(@PathVariable Integer productId,
                                            @PathVariable Integer warehouseId,
                                            @RequestParam(required = false) Character order) {
        Warehouse warehouse = warehouseService.findById(warehouseId);
        Product product = productService.findById(productId);
        if(order != null){
            List<Character> validCharacters = Arrays.asList('L', 'C', 'F', 'l', 'c', 'f');
            if(validCharacters.contains(order))
                return ResponseEntity.ok(productService.filterProductInWarehouse(warehouse, product, order));
            else
                throw new RuntimeException("Letra de ordenação inserida é inválida.\n" +
                        "Letras válidas: " +
                        "\n\nL = Para obter a lista de lotes do produto no armazém informado, ordenado pelo número do lote;" +
                        "\nC = Para obter a lista de lotes do produto no armazém informado, ordenado pela quantidade em atual estoque;" +
                        "\nF = Para obter a lista de lotes do produto no armazém informado, ordenado pela data de validade."
                );
        }
        return ResponseEntity.ok(productService.filterProductInWarehouse(warehouse, product, ' '));
    }

    @GetMapping("/warehouse/{productId}")
    public ResponseEntity<?> getProductInWarehouse(@PathVariable Integer productId){
        return ResponseEntity.ok(productService.countProductInWarehouse(productId));
    }

    @GetMapping("/due-date/{days}/{sectionId}")
    public ResponseEntity<?> getProductsNearOfExpirationDate(@PathVariable Integer days, @PathVariable Integer sectionId){
        if (!days.equals(0)){
            List<ProductNearExpireDateDTO> productList = productService.getProductsNearOfExpiraionDate(days, sectionId).stream()
                    .map(ProductNearExpireDateDTO::convertToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(productList);
        } else{
            throw new NotFoundException("Não é possível realizar busca com esse valor!");
        }
    }

    @GetMapping("/due-date-all/{days}/{category}")
    public ResponseEntity<?> getProductsNearOfExpirationDate(@PathVariable Integer days, @PathVariable String category, @RequestParam(required = false) String order){
        if (!days.equals(0)){
            List<ProductNearExpireDateDTO> productList = productService.getProductsNearOfExpiraionDate(days, category, order).stream()
                    .map(ProductNearExpireDateDTO::convertToDTO)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(productList);
        } else{
            throw new NotFoundException("Não é possível realizar busca com esse valor!");
        }
    }
}
