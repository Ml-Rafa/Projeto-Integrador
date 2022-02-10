package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.InboundOrderDTO;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @PostMapping("/register-inbound-order")
    public ResponseEntity<?> registerInboundOrder(@Valid @RequestBody InboundOrderDTO inboundOrderDTO, UriComponentsBuilder uriBuilder) {
        InboundOrder inboundOrder = this.inboundOrderService.convertToEntity(inboundOrderDTO);
        if (inboundOrder == null)
            return ResponseEntity.badRequest().body("Não foi possível registrar a inbound order.");
        inboundOrderService.create(inboundOrder);
        return ResponseEntity.created(uriBuilder
                .path("/register-inbound-order")
                .buildAndExpand("register")
                .toUri()).body(InboundOrderDTO.convertToDTO(inboundOrder));
    }

    @PutMapping("/update-inbound-order/{id}")
    public ResponseEntity<?> updateInboundOrder(@PathVariable Integer id, @Valid @RequestBody InboundOrderDTO inboundOrder) {
        inboundOrder.setOrderNumber(id);
        InboundOrder entity = this.inboundOrderService.convertToEntity(inboundOrder);
        if (entity == null)
            return ResponseEntity.badRequest().body("Não foi possível atualizar a inbound order.");
        inboundOrderService.update(entity);
        return ResponseEntity.status(201).body(InboundOrderDTO.convertToDTO(entity));
    }
}
