package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.repositories.DeliveryDatesRepository;
import br.com.meli.wave4.repositories.DeliveryTimeByStateInHoursRepository;
import br.com.meli.wave4.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
