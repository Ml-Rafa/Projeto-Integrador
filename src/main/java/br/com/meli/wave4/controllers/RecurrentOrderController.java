package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.services.RecurrentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recurrent-order")
public class RecurrentOrderController {

    @Autowired
    RecurrentOrderService recurrentOrderService;

    @GetMapping("list")
    public ResponseEntity<List<PurchaseOrderDTO>> processRecurrentOrder(){
        return ResponseEntity.ok(this.recurrentOrderService.processRecurrentOrder());
    }


}
