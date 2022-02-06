package br.com.meli.wave4.controllers;

import br.com.meli.wave4.DTO.InboundOrderDTO;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.services.InboundOrderService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
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

        inboundOrderService.create(inboundOrder);
        return ResponseEntity.created(uriBuilder
        .path("/register-inbound-order")
        .buildAndExpand("register")
        .toUri()).body(InboundOrderDTO.convertToDTO(inboundOrder));
//        .toUri()).body(inboundOrder);

    }

    @PutMapping("/update-inbound-order/{id}")
    public ResponseEntity<?> updateInboundOrder(@PathVariable Integer id, @RequestBody InboundOrderDTO inboundOrder) {

        inboundOrder.setOrderNumber(id);

        InboundOrder entity = this.inboundOrderService.convertToEntity(inboundOrder);
        inboundOrderService.update(entity);
        return ResponseEntity.status(201).body(InboundOrderDTO.convertToDTO(entity));
    }
}
