package br.com.meli.wave4.controllers;
import br.com.meli.wave4.DTO.*;
import br.com.meli.wave4.entities.Schedule;
import br.com.meli.wave4.exceptions.NotFoundException;
import br.com.meli.wave4.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products/scheduling")
public class SchedulingController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/availableDates/{purchaseOrderId}")
    public ResponseEntity<?> getAvailableDates(@PathVariable Integer purchaseOrderId) {
        try{
            List<DeliveryDatesDTO> deliveryDatesDTO = DeliveryDatesDTO.convertToDTO(scheduleService.getAvailableDates(purchaseOrderId));

            return deliveryDatesDTO.isEmpty()
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(DeliveryDatesDTO.convertToDTO(scheduleService.getAvailableDates(purchaseOrderId)));
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body("PurchaseOrderId informado é inválido.");
        }
    }

    @GetMapping("/calculateShipping/{purchaseOrderId}")
    public ResponseEntity<?> calculateShipping(@PathVariable Integer purchaseOrderId) {
        try {
            return ResponseEntity.ok(scheduleService.calculateShipping(purchaseOrderId));
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body("PurchaseOrderId informado é inválido.");
        }
    }


    @GetMapping("/schedules")
    public ResponseEntity<?> getAllSchedules() {
        List<Schedule> scheduleListPersistence = scheduleService.findAll();
        List<ScheduleDTO> scheduleDTOList = scheduleListPersistence.stream().map(scheduleService::convertToDTO).collect(Collectors.toList());
        return scheduleListPersistence.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(scheduleDTOList);
    }


    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable Integer scheduleId) {
        try{
            Schedule schedule = scheduleService.getById(scheduleId);
            if(schedule != null)
                return ResponseEntity.ok(scheduleService.convertToDTO(schedule));
            throw new NotFoundException("Schedule não localizada.");
        } catch (NullPointerException e){
            return ResponseEntity.badRequest().body("ScheduleId informado é inválido.");
        }
    }

    @PostMapping("/schedules/register")
    public ResponseEntity<?> registerSchedule(@Valid @RequestBody ScheduleDTO scheduleDTO, UriComponentsBuilder uriBuilder) {
            Schedule scheduleEntity = scheduleService.convertToEntity(scheduleDTO);
            if (scheduleEntity == null)
                return ResponseEntity.badRequest().body("Não foi possível realizar o agendamento.");
            return ResponseEntity.created(uriBuilder
                    .path("/schedules/register")
                    .buildAndExpand("register")
                    .toUri()).body(scheduleService.convertToDTO(scheduleService.registerSchedule(scheduleEntity)));

    }

    @PutMapping("/schedules/update/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer scheduleId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        try{
            scheduleDTO.setId(scheduleId);
            Schedule scheduleEntity = scheduleService.convertToEntity(scheduleDTO);
            return ResponseEntity.ok(scheduleService.convertToDTO(scheduleService.updateSchedule(scheduleEntity)));
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("ScheduleId informado é inválido.");
        }
    }

    @DeleteMapping("/schedules/delete/{scheduleId}")
    public ResponseEntity<?> cancelScheduling(@PathVariable Integer scheduleId) {
        try{
            scheduleService.cancelScheduling(scheduleId);
            return ResponseEntity.ok("Schedule cancelada.");
        }catch (NullPointerException e){
            return ResponseEntity.badRequest().body("ScheduleId informado é inválido.");
        }
    }
}
