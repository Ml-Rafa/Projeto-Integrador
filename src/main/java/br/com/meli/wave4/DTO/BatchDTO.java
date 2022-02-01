package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class BatchDTO {

    private Integer batchNumber;
    private Integer productId;
    private Integer representativeId;
    private Double currentTemperature;
    private Double minimumTemperature;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
    private Integer inboundOrderId;

    public static BatchDTO convertToDTO(Batch batch) {

        return BatchDTO.builder()
                .batchNumber(batch.getBatchNumber())
                .currentQuantity(batch.getCurrentQuantity())
                .initialQuantity(batch.getInitialQuantity())
                .productId(batch.getProduct().getId())
                .currentTemperature(batch.getCurrentTemperature())
                .minimumTemperature(batch.getMinimumTemperature())
                .representativeId(batch.getRepresentative().getId())
                .dueDate(batch.getDueDate())
                .inboundOrderId(batch.getInboundOrder().getOrderNumber())
                .manufacturingDate(batch.getManufacturingDate())
                .manufacturingTime(batch.getManufacturingTime())
                .build();
    }
}
