package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class ProductDiscountDTO {
    private String name;
    private LocalDate dueDate;
    private BigDecimal price;
    private Integer discountOfDueDate;
    private Integer currentQuantity;

    public static ProductDiscountDTO convertToDTO(Batch batch){
        return ProductDiscountDTO.builder()
                .name(batch.getProduct().getName())
                .dueDate(batch.getDueDate())
                .price(batch.getProduct().getPrice())
                .discountOfDueDate(batch.getDiscountOfDueDate())
                .currentQuantity(batch.getCurrentQuantity())
                .build();
    }
}
