package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.ArticlesPurchase;
import br.com.meli.wave4.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {

    private Integer id;
    private LocalDate date;
    private Integer clientId;
    private OrderStatus orderStatus;
    private Set<ArticlesPurchaseDTO> articlesPurchases;
    private BigDecimal totalPrice;
}
