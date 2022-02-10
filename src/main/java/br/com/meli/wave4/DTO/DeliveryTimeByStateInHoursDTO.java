package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.DeliveryTimeByStateInHours;
import br.com.meli.wave4.entities.Warehouse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class DeliveryTimeByStateInHoursDTO {

    private Integer id;
    private String state;
    private Warehouse warehouse;
    private Integer hours;
    private BigDecimal shippingValue;

    public static DeliveryTimeByStateInHoursDTO convertToDTO(DeliveryTimeByStateInHours deliveryTimeByStateInHours){
        return DeliveryTimeByStateInHoursDTO.builder()
                .id(deliveryTimeByStateInHours.getId())
                .hours(deliveryTimeByStateInHours.getHours())
                .shippingValue(deliveryTimeByStateInHours.getShippingValue())
                .state(deliveryTimeByStateInHours.getState())
                .warehouse(deliveryTimeByStateInHours.getWarehouse())
                .build();
    }
}
