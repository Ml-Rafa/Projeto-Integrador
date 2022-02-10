package br.com.meli.wave4.controllers;
import br.com.meli.wave4.DTO.*;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.entities.Schedule;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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
        return null;
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable Integer scheduleId) {
        try{
            return null;
        }catch (NullPointerException e){
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
            Schedule scheduleEntity = scheduleService.convertToEntity(scheduleDTO);
            return ResponseEntity.ok(scheduleService.updateSchedule(scheduleEntity));
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
