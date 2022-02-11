package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ScheduleDTO {

    private Integer id;
    @NotNull(message = "O Id da ordem de compra não pode ser nulo.")
    @PositiveOrZero(message = "O Id da ordem de compra não pode ser menor do que zero.")
    private Integer purchaseOrderId;
    @NotNull(message = "A data de entrega não pode ser nula.")
    @FutureOrPresent(message = "A data de entrega não pode ser pretérita.")
    private LocalDateTime deliveryDateTime;

    private BigDecimal shippingValue;
}







