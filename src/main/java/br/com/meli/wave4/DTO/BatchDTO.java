package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class BatchDTO {

    private Integer batchNumber;
    @NotNull(message = "O campo productId não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo productId não pode ser negativo.")
    private Integer productId;
//    @NotNull(message = "O número do ID do representante deve ser informado.")
//    private Integer representativeId;
    @NotNull(message = "O campo currentTemperature não pode ser nulo.")
    private Double currentTemperature;
    @NotNull(message = "O campo minimumTemperature não pode ser nulo.")
    private Double minimumTemperature;
    @NotNull(message = "O campo initialQuantity não pode ser nulo.")
    @PositiveOrZero(message = "O campo initialQuantity não pode ser menor do que zero.")
    private Integer initialQuantity;
    @NotNull(message = "O campo currentQuantity não pode ser nulo.")
    @PositiveOrZero(message = "O campo currentQuantity não pode ser menor do que zero.")
    private Integer currentQuantity;
    @PastOrPresent(message = "O campo manufacturingDate não pode receber uma data futura.")
    @NotNull(message = "O campo manufacturingDate não pode ser nulo.")
    private LocalDate manufacturingDate;
    @PastOrPresent(message = "O campo manufacturingTime não pode receber uma data/hora futura.")
    @NotNull(message = "O campo manufacturingTime não pode ser nulo.")
    private LocalDateTime manufacturingTime;
    @FutureOrPresent(message = "O campo dueDate não pode receber uma data pretérita.")
    @NotNull(message = "O campo dueDate não pode ser nulo.")
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
//                .representativeId(batch.getRepresentative().getId())
                .dueDate(batch.getDueDate())
                .inboundOrderId(batch.getInboundOrder().getOrderNumber())
                .manufacturingDate(batch.getManufacturingDate())
                .manufacturingTime(batch.getManufacturingTime())
                .build();
    }
}
