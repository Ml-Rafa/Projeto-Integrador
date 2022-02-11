package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.DeliveryDates;
import br.com.meli.wave4.entities.Schedule;

import java.math.BigDecimal;
import java.util.List;

public interface IScheduleService {

    List<DeliveryDates> getAvailableDates(Integer purchaseOrderId);
    BigDecimal calculateShipping(Integer purchaseOrderId);
    Schedule registerSchedule(Schedule schedule);
    Schedule updateSchedule (Schedule schedule);
    void cancelScheduling(Integer scheduleId);
    List<Schedule> findAll();
    Schedule getById(Integer scheduleId);

}
