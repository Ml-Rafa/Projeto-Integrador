package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
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
    @PastOrPresent(message = "O campo date não pode receber uma data futura.")
    @NotNull(message = "O campo orderDate não pode ser nulo.")
    private LocalDate orderDate;
    @NotNull(message = "O campo sectionCode não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo sectionCode não pode ser negativo.")
    private Integer sectionCode;
    @NotNull(message = "O campo warehouseCode não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo warehouseCode não pode ser negativo.")
    private Integer warehouseCode;
    @NotNull(message = "O campo sellerId não pode ser nulo.")
    @PositiveOrZero(message = "O número do campo sellerId não pode ser negativo.")
    private Integer sellerId;
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
                .sellerId(inboundOrder.getSellerId())
                .batchStock(batchDTOList)
                .build();
    }
}
