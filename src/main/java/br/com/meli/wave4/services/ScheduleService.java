package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.DeliveryDates;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.entities.Schedule;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.repositories.DeliveryDatesRepository;
import br.com.meli.wave4.repositories.DeliveryTimeByStateInHoursRepository;
import br.com.meli.wave4.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class ScheduleService {

    @Autowired
    DeliveryDatesRepository deliveryDatesRepository;

    @Autowired
    DeliveryTimeByStateInHoursRepository deliveryTimeByStateInHoursRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .purchaseOrder(purchaseOrderService.findById(scheduleDTO.getPurchaseOrderId()))
                .deliveryDateTime(scheduleDTO.getDeliveryDateTime())
                .build();
    }
    public ScheduleDTO convertToDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .purchaseOrderId(schedule.getId())
                .deliveryDateTime(schedule.getDeliveryDateTime())
                .shippingValue(schedule.getShippingValue())
                .build();
    }

    public List<DeliveryDates> getAvailableDates(Integer purchaseOrderId){
        PurchaseOrder purchaseOrderPersistence = purchaseOrderService.findById(purchaseOrderId);
        User client = purchaseOrderPersistence.getClient();
        String stateClientDelivery = client.getState();
        purchaseOrderPersistence.
        deliveryDatesRepository.
        return null;
    }
}
