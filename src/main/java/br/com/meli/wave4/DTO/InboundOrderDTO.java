package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.entities.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDate orderDate;
    private Integer sectionCode;
    private Integer warehouseCode;
    private List<BatchDTO> batchStock = new ArrayList<>();

    public static InboundOrderDTO convertToDTO(InboundOrder inboundOrder) {
        List<Batch> batchList = inboundOrder.getBatchStock();
        System.out.println("INboundOrder: " + inboundOrder.getOrderNumber());
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
