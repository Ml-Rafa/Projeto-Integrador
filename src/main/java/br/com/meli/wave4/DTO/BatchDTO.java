package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class BatchDTO {

    private Integer batchNumber;
    @NotNull(message = "O número do ID do produto deve ser informado.")
    private Integer productId;
//    @NotNull(message = "O número do ID do representante deve ser informado.")
//    private Integer representativeId;
    @NotNull(message = "A temperatura atual deve ser informada.")
    private Double currentTemperature;
    @NotNull(message = "A temperatura mínima deve ser informada.")
    private Double minimumTemperature;
    @NotNull(message = "A quantidade inicial do estoque deve ser informada.")
    private Integer initialQuantity;
    @NotNull(message = "A quantidade atual do estoque deve ser informada.")
    private Integer currentQuantity;
//    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "A data deve ter o seguinte formato: YYYY-MM-DD")
    private LocalDate manufacturingDate;
//    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}Z", message = "A data e hora deve ter o seguinte formato: yyyy-mm-ddThh:mm:ssZ")
    private LocalDateTime manufacturingTime;
//    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "A data deve ter o seguinte formato: YYYY-MM-DD")
    private LocalDate dueDate;
//    @NotNull(message = "O número do ID da inboundOrder deve ser informado.")
    private Integer inboundOrderId;

    public static BatchDTO convertToDTO(Batch batch) {

        return BatchDTO.builder()
                .batchNumber(batch.getBatchNumber())
                .currentQuantity(batch.getCurrentQuantity())
                .initialQuantity(batch.getInitialQuantity())
                .productId(batch.getProduct().getId())
                .currentTemperature(batch.getCurrentTemperature())
                .minimumTemperature(batch.getMinimumTemperature())
//                .representativeId(batch.getRepresentative().getId())
                .dueDate(batch.getDueDate())
                .inboundOrderId(batch.getInboundOrder().getOrderNumber())
                .manufacturingDate(batch.getManufacturingDate())
                .manufacturingTime(batch.getManufacturingTime())
                .build();
    }
}
