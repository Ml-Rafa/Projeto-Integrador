package br.com.meli.wave4.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class BatchSimpleResponseDTO implements List<T> {

    private Integer batchNumber;
    private Integer currentQuantity;
    private LocalDate dueDate;
}
