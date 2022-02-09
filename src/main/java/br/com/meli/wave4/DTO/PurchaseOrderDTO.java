package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {

    private Integer id;
    @PastOrPresent(message = "O campo date não pode receber uma data futura.")
    @NotNull(message = "O campo date não pode ser nulo.")
    private LocalDate date;
//    @NotNull(message = "O campo clientId não pode ser nulo.")
//    @PositiveOrZero(message = "O número do campo clientId não pode ser negativo.")
//    private Integer clientId;
    @NotNull(message = "O campo orderStatus não pode ser nulo.")
    private OrderStatus orderStatus;
    @NotNull(message = "O campo articlesPurchase não pode ser nulo.")
    private List<ArticlesPurchaseDTO> articlesPurchases;


    private List<String> mensagem;

    private BigDecimal totalPrice;


}
