package br.com.meli.wave4.controllers;


import br.com.meli.wave4.DTO.ProductDTO;
import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.DTO.ScheduleDTO;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.entities.Schedule;
import br.com.meli.wave4.entities.TypeRefrigeration;
import br.com.meli.wave4.exceptions.InsufficientStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products/scheduling")
public class SchedulingController {

    @GetMapping("/availableDates/{purchaseOrderId}")
    public ResponseEntity<?> getAvailableDates(@PathVariable Integer purchaseOrderId) {
        return null;
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
    public ResponseEntity<?> registerSchedule(@PathVariable Integer purchaseOrderId, @RequestBody ScheduleDTO scheduleDTO) {
        return null;
    }

    @PutMapping("/schedules/update/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer scheduleId, @RequestBody ScheduleDTO scheduleDTO) {
        return null;
    }

    @DeleteMapping("/schedules/delete/{scheduleId}")
    public ResponseEntity<?> order(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO, UriComponentsBuilder uriBuilder) throws InsufficientStockException {
        return null;
    }
}
