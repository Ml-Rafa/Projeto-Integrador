package br.com.meli.wave4.controllers;
import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import br.com.meli.wave4.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/scheduling")
public class SchedulingController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping("/availableDates/{purchaseOrderId}")
    public ResponseEntity<?> getAvailableDates(@PathVariable Integer purchaseOrderId) {
        return ResponseEntity.ok(scheduleService.getAvailableDates(purchaseOrderId));
    }

    @GetMapping("/calculateShipping/{purchaseOrderId}")
    public ResponseEntity<?> calculateShipping(@PathVariable Integer purchaseOrderId) {
        return null;
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> getAllSchedules() {
        return null;
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getSchedule(@PathVariable Integer scheduleId) {
        return null;
    }

    @PostMapping("/schedules/register/{purchaseOrderId}")
    public ResponseEntity<?> registerSchedule(@PathVariable Integer purchaseOrderId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        return null;
    }

    @PutMapping("/schedules/update/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer scheduleId, @Valid @RequestBody ScheduleDTO scheduleDTO) {
        return null;
    }

    @DeleteMapping("/schedules/delete/{scheduleId}")
    public ResponseEntity<?> order(@PathVariable Integer scheduleId) {
        return null;
    }
}
