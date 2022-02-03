package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.ProductNearExpireDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.PushBuilder;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductNearExpireDateDTO {
    private Integer batchNumber;
    private Integer productId;
    private Integer typeRefrigerated;
    private Integer currentQuantity;
    private LocalDate dueDate;


    public static ProductNearExpireDateDTO convertToDTO(ProductNearExpireDate productNearExpireDate){
        return ProductNearExpireDateDTO.builder()
                .batchNumber(productNearExpireDate.getBatchNumber())
                .productId(productNearExpireDate.getProductId())
                .typeRefrigerated(productNearExpireDate.getTypeRefrigerated())
                .currentQuantity(productNearExpireDate.getCurrentQuantity())
                .dueDate(productNearExpireDate.getDueDate())
                .build();
    }
}
