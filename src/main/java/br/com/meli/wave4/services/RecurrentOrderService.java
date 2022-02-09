package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.RecurrentOrder;
import br.com.meli.wave4.repositories.RecurrentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class RecurrentOrderService {

    @Autowired
    RecurrentOrderRepository recurrentOrderRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;


    public List<RecurrentOrder> getRecurrentActiveOrderToDay(){
        return this.recurrentOrderRepository.findByNextDateAndActive(LocalDate.now(),true);
    }

    public List<PurchaseOrderDTO> processRecurrentOrder(){

        List<PurchaseOrderDTO> purchaseOrderDTOList = new ArrayList<>();
        List<RecurrentOrder>  recurrentActiveOrderToDay = this.getRecurrentActiveOrderToDay();
        for(RecurrentOrder r : recurrentActiveOrderToDay){
            purchaseOrderDTOList.add(this.purchaseOrderService.order(r.getPurchaseOrder()));
        }
        return  purchaseOrderDTOList;
    }

    public List<RecurrentOrder> listAll(){
        return  this.recurrentOrderRepository.findAll();
    }

}
