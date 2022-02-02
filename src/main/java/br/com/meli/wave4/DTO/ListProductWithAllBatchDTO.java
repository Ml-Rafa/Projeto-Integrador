package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.Section;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class ListProductWithAllBatchDTO {

    private Integer sectionCode;
    private Integer warehouseCode;
    private Integer productId;
    private List<BatchSimpleResponseDTO> batchStock = new ArrayList<>();

}
