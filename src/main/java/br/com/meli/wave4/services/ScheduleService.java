package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.*;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.exceptions.UnavailableDateException;
import br.com.meli.wave4.repositories.*;
import br.com.meli.wave4.services.iservices.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    DeliveryDatesRepository deliveryDatesRepository;

    @Autowired
    DeliveryTimeByStateInHoursRepository deliveryTimeByStateInHoursRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    BatchService batchService;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    WarehouseService warehouseService;

    BatchRepository batchRepository;
    ProductRepository productRepository;
    RepresentativeService representativeService;
    UserRepository userRepository;

    public ScheduleService(
            ScheduleRepository scheduleRepository,
            DeliveryTimeByStateInHoursRepository deliveryTimeByStateInHoursRepositoryMock,
            DeliveryDatesRepository deliveryDatesRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            PurchaseOrderService purchaseOrderService,
            BatchService batchService,
            BatchRepository batchRepository,
            ProductRepository productRepository,
            RepresentativeService representativeService,
            UserRepository userRepository,
            WarehouseRepository warehouseRepository,
            WarehouseService warehouseService
    ) {
        this.scheduleRepository = scheduleRepository;
        this.deliveryTimeByStateInHoursRepository = deliveryTimeByStateInHoursRepositoryMock;
        this.deliveryDatesRepository = deliveryDatesRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderService = purchaseOrderService;
        this.batchService = batchService;
        this.batchRepository = batchRepository;
        this.productRepository = productRepository;
        this.representativeService = representativeService;
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
        this.warehouseService = warehouseService;
    }

    public Schedule convertToEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .id(scheduleDTO.getId())
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

    @Override
    public List<DeliveryDates> getAvailableDates(Integer purchaseOrderId){
        PurchaseOrder purchaseOrderPersistence = this.purchaseOrderService.findById(purchaseOrderId);
        User client = purchaseOrderPersistence.getClient();
        String stateClientDelivery = client.getState();
        List<DeliveryTimeByStateInHours> deliveryTimeByStateInHours = deliveryTimeByStateInHoursRepository
                .findAllByState(stateClientDelivery);

        List<Warehouse> warehouses = new ArrayList<>();
        purchaseOrderPersistence.getArticlesPurchases().forEach(
                a -> {
                    Batch batch = this.batchService.findByBatchNumber(a.getBatchCode());
                    warehouses.add(batch.getSection().getWarehouse());
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

        List<DeliveryDates> deliveryDates =
                deliveryDatesRepository.findAllByDeliveryLocationAndDateIsAvailable(client.getState(), true);
        Integer finalMaxTime = maxTime;
        List<DeliveryDates> deliveryDatesFiltered = deliveryDates.stream().filter(d-> d.getDateTime().isAfter(LocalDateTime.now().plusHours(finalMaxTime)) && d.getDeliveryLocation().equals(client.getState())).collect(Collectors.toList());
        return deliveryDatesFiltered;
    }

    @Override
    public BigDecimal calculateShipping(Integer purchaseOrderId){
        PurchaseOrder purchaseOrderPersistence = purchaseOrderService.findById(purchaseOrderId);
        User client = purchaseOrderPersistence.getClient();
        String stateClientDelivery = client.getState();
        List<DeliveryTimeByStateInHours> deliveryTimeByStateInHours = deliveryTimeByStateInHoursRepository
                .findAllByState(stateClientDelivery);

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

    @Override
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

    @Override
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

    @Override
    public void cancelScheduling(Integer scheduleId){
        Schedule schedulePersistence = scheduleRepository.findById(scheduleId).orElse(null);
        if(schedulePersistence != null)
            scheduleRepository.delete(schedulePersistence);
        else
            throw new NotFoundException("Schedule não encontrada no sistema.");
    }

    @Override
    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule getById(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }
}
