package br.com.meli.wave4.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SellerDTO {
    private Integer id;
    private String name;
    private String document;
//    private List<ProductDTO> productDTOList;

}