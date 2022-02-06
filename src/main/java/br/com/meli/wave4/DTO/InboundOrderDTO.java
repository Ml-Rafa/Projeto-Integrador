package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    private Integer orderNumber;
//    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "A data deve ter o seguinte formato: YYYY-MM-DD")
    private LocalDate orderDate;
    private Integer sectionCode;
    private Integer warehouseCode;
    @Valid
    private List<BatchDTO> batchStock = new ArrayList<>();

    public static InboundOrderDTO convertToDTO(InboundOrder inboundOrder) {
        List<Batch> batchList = inboundOrder.getBatchStock();
        List<BatchDTO> batchDTOList = batchList.stream().map(BatchDTO::convertToDTO).collect(Collectors.toList());
        return InboundOrderDTO.builder()
                .orderDate(inboundOrder.getOrderDate())
                .orderNumber(inboundOrder.getOrderNumber())
                .sectionCode(inboundOrder.getSection().getSectionCode())
                .warehouseCode(inboundOrder.getSection().getWarehouse().getId())
                .batchStock(batchDTOList)
                .build();
    }
}
