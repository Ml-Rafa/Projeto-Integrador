package br.com.meli.wave4.controllers;

import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
public class InboundOrderController {

    @Autowired
    private InboundOrderService inboundOrderService;

    @PostMapping("/register-inbound-order")
    public ResponseEntity<?> registerInboundOrder(@RequestBody InboundOrder inboundOrder) {
        return null;
    }

    @PutMapping("/update-inbound-order")
    public ResponseEntity<?> updateInboundOrder(@RequestBody InboundOrder inboundOrder) {
        return null;
    }
}
