package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundOrderDTO {

    private List<Batch> batchStock = new ArrayList<>();

    public static InboundOrderDTO convertToDTO(InboundOrder inboundOrder) {
        return InboundOrderDTO.builder()
                .batchStock(inboundOrder.getBatchStock())
                .build();
    }
}
