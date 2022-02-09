package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.TypeRefrigeration;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private TypeRefrigeration sectionTypeRefrigerated;
    private LocalDate dateValid;
    private BigDecimal price;
    private List<BatchDTO> batchList = new ArrayList<>();
    private SellerDTO seller;



}
