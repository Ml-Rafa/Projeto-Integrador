package br.com.meli.wave4.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesPurchaseDTO {

    @NotNull(message = "O campo product não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo product não pode ser negativo.")
    private Integer product;
    @NotNull(message = "O campo quantity não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo quantity não pode ser negativo.")
    private Integer quantity;
    @NotNull(message = "O campo batchCode não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo batchCode não pode ser negativo.")
    private Integer batchCode;
}
