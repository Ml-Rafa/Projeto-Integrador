package br.com.meli.wave4.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ListProductInSectionDTO {

    private String productName;
    private Integer quantity;
    private Integer batchNumber;
    private Integer sectionCode;
}
