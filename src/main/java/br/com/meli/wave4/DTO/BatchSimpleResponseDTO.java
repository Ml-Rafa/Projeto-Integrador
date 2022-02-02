package br.com.meli.wave4.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
@Data
public class BatchSimpleResponseDTO {

    private Integer batchNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;
}
