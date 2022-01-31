package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.InboundOrderDTO;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @PostMapping("/register-inbound-order")
    public ResponseEntity<?> registerInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrder = this.inboundOrderService.convertToEntity(inboundOrderDTO);

        inboundOrderService.saveInboundOrder(inboundOrder);

        return ResponseEntity.created(uriBuilder
        .path("/register-inbound-order")
        .buildAndExpand("register")
        .toUri()).body(InboundOrderDTO.convertToDTO(inboundOrder));

    }

    @PutMapping("/update-inbound-order/{id}")
    public ResponseEntity<?> updateInboundOrder(@RequestParam Integer id, @RequestBody InboundOrder inboundOrder) {
        inboundOrderService.updateById(inboundOrder);
        return ResponseEntity.status(201).body(inboundOrder);
    }

    @GetMapping
    public String saudacao(){
        return "Olá";
    }
}
