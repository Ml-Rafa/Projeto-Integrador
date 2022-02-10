package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.DTO.RecurrentOrderDTO;
import br.com.meli.wave4.services.RecurrentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recurrent-order")
public class RecurrentOrderController {

    @Autowired
    RecurrentOrderService recurrentOrderService;

    @GetMapping("/list")
    public ResponseEntity<?> processRecurrentOrder(){
        return ResponseEntity.ok(this.recurrentOrderService.processRecurrentOrder());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody RecurrentOrderDTO recurrentOrderDTO){
        try{
            return ResponseEntity.ok(this.recurrentOrderService.create(recurrentOrderDTO));
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
