package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.exceptions.UnavailableDateException;
import br.com.meli.wave4.repositories.DeliveryDatesRepository;
import br.com.meli.wave4.repositories.DeliveryTimeByStateInHoursRepository;
import br.com.meli.wave4.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

    @Autowired
    BatchService batchService;

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .purchaseOrder(purchaseOrderService.findById(scheduleDTO.getPurchaseOrderId()))
                .deliveryDateTime(scheduleDTO.getDeliveryDateTime())
                .build();
    }
    public ScheduleDTO convertToDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .purchaseOrderId(schedule.getId())
                .deliveryDateTime(schedule.getDeliveryDateTime())
                .shippingValue(schedule.getShippingValue())
                .build();
    }

    public List<DeliveryDates> getAvailableDates(Integer purchaseOrderId){
        PurchaseOrder purchaseOrderPersistence = purchaseOrderService.findById(purchaseOrderId);
        User client = purchaseOrderPersistence.getClient();
        String stateClientDelivery = client.getState();
        List<DeliveryTimeByStateInHours> deliveryTimeByStateInHours = deliveryTimeByStateInHoursRepository
                .findAllByState(stateClientDelivery);

        //VERIFICAR A WAREHOUSE/TEMPO MAIS LONGE QUE FOI FEITA A COMPRA. VERIFICAR O PRAZO DE ENTREGA
        Set<Warehouse> warehouses = new HashSet<>();
        purchaseOrderPersistence.getArticlesPurchases().forEach(
                a -> {
                    Batch batch = batchService.findByBatchNumber(a.getBatchCode());
                    Warehouse warehouse = batch.getSection().getWarehouse();
                    warehouses.add(warehouse);
                }
        );

        Integer maxTime = 0;
        Integer deliveryTimeHours = 0;
        for (Warehouse warehouse : warehouses) {
            DeliveryTimeByStateInHours deliveryTime = deliveryTimeByStateInHoursRepository.findByStateAndWarehouse(stateClientDelivery, warehouse);

            deliveryTimeHours = deliveryTime.getHours();
            if(deliveryTimeHours > maxTime){
                maxTime = deliveryTimeHours;
            }
        }
        //FILTRAR SOMENTE AS DATAS DISPONÍVEIS QUE SÃO APÓS O PRAZO DE ENTREGA
        List<DeliveryDates> deliveryDates =
                deliveryDatesRepository.findAllByDeliveryLocationAndDateIsAvailable(client.getState(), true);
        Integer finalMaxTime = maxTime;
        List<DeliveryDates> deliveryDatesFiltered = deliveryDates.stream().filter(d-> d.getDateTime().isAfter(LocalDateTime.now().plusHours(finalMaxTime)) && d.getDeliveryLocation().equals(client.getState())).collect(Collectors.toList());
        return deliveryDatesFiltered;
    }

    public BigDecimal calculateShipping(Integer purchaseOrderId){
        PurchaseOrder purchaseOrderPersistence = purchaseOrderService.findById(purchaseOrderId);
        User client = purchaseOrderPersistence.getClient();
        String stateClientDelivery = client.getState();
        List<DeliveryTimeByStateInHours> deliveryTimeByStateInHours = deliveryTimeByStateInHoursRepository
                .findAllByState(stateClientDelivery);

        //VERIFICAR A WAREHOUSE/TEMPO MAIS LONGE QUE FOI FEITA A COMPRA. VERIFICAR O FRETE DE ENTREGA
        Set<Warehouse> warehouses = new HashSet<>();
        purchaseOrderPersistence.getArticlesPurchases().forEach(
                a -> {
                    Batch batch = batchService.findByBatchNumber(a.getBatchCode());
                    Warehouse warehouse = batch.getSection().getWarehouse();
                    warehouses.add(warehouse);
                }
        );

        Integer maxTime = 0;
        BigDecimal deliveryValueShipping = new BigDecimal(0);
        for (Warehouse warehouse : warehouses) {
            DeliveryTimeByStateInHours deliveryTime = deliveryTimeByStateInHoursRepository.findByStateAndWarehouse(stateClientDelivery, warehouse);

            if(deliveryTime.getHours() > maxTime){
                maxTime = deliveryTime.getHours();
                deliveryValueShipping = deliveryTime.getShippingValue();
            }
        }
      return deliveryValueShipping;
    }
    public Schedule registerSchedule(Schedule schedule){
        List<DeliveryDates> deliveryDates = this.getAvailableDates(schedule.getPurchaseOrder().getId());
        Integer count = 0;
        for(DeliveryDates d : deliveryDates){
            if(schedule.getDeliveryDateTime().equals(d.getDateTime())) {
                count++;
            }
        }
        if(count > 0){
            schedule.setShippingValue(this.calculateShipping(schedule.getPurchaseOrder().getId()));
            return scheduleRepository.save(schedule);
        }
        throw new UnavailableDateException();
    }

    public Schedule updateSchedule (Schedule schedule){

        List<DeliveryDates> deliveryDates = this.getAvailableDates(schedule.getPurchaseOrder().getId());
        Integer count = 0;
        for(DeliveryDates d : deliveryDates){
            if(schedule.getDeliveryDateTime().equals(d.getDateTime())) {
                count++;
            }
        }
        if(count > 0){
            Schedule schedulePersistence = scheduleRepository.findById(schedule.getId()).orElse(null);
            assert schedulePersistence != null;
            schedulePersistence.setDeliveryDateTime(schedule.getDeliveryDateTime());
            schedulePersistence.setPurchaseOrder(schedule.getPurchaseOrder());
            return scheduleRepository.saveAndFlush(schedulePersistence);
        }
        throw new UnavailableDateException();
    }

    public void cancelScheduling(Integer scheduleId){
        Schedule schedulePersistence = scheduleRepository.findById(scheduleId).orElse(null);
        if(schedulePersistence != null)
            scheduleRepository.delete(schedulePersistence);
        else
            throw new NotFoundException("Schedule não encontrada no sistema.");
    }
}
