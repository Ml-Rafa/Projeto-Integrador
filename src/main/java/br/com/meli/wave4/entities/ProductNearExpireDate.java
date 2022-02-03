package br.com.meli.wave4.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ProductNearExpireDate {
    private Integer batchNumber;
    private Integer productId;
    private Integer typeRefrigerated;
    private Integer currentQuantity;
    private LocalDate dueDate;

    public ProductNearExpireDate(Integer batchNumber, Integer productId, Integer typeRefrigerated, Integer currentQuantity, LocalDate dueDate) {
        this.batchNumber = batchNumber;
        this.productId = productId;
        this.typeRefrigerated = typeRefrigerated;
        this.currentQuantity = currentQuantity;
        this.dueDate = dueDate;
    }
}
