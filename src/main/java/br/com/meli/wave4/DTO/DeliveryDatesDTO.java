package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.DeliveryDates;
import br.com.meli.wave4.entities.DeliveryTimeByStateInHours;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class DeliveryDatesDTO {

    private Integer id;
    private String deliveryLocation;
    private LocalDateTime dateTime;
    private Boolean dateIsAvailable;

    public static List<DeliveryDatesDTO> convertToDTO(List<DeliveryDates> deliveryDates){
        List<DeliveryDatesDTO> deliveryDatesDTOS = new ArrayList<>();
        for(DeliveryDates d : deliveryDates){
            deliveryDatesDTOS.add(
                    DeliveryDatesDTO.builder()
                            .id(d.getId())
                            .deliveryLocation(d.getDeliveryLocation())
                            .dateTime(d.getDateTime())
                            .dateIsAvailable(d.getDateIsAvailable())
                            .build()
            );
        }
        return deliveryDatesDTOS;
    }
}
