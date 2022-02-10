package br.com.meli.wave4.services.iservices;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.PurchaseOrder;

public interface IPurchaseOrderService {


    PurchaseOrderDTO order(PurchaseOrder purchaseOrder);
}
