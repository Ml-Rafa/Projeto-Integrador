package br.com.meli.wave4.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesPurchaseDTO {


    private Integer product;
    private Integer quantity;

}
