package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.DTO.RecurrentOrderDTO;
import br.com.meli.wave4.entities.PurchaseOrder;
import br.com.meli.wave4.entities.RecurrentOrder;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.repositories.RecurrentOrderRepository;
import net.bytebuddy.implementation.bytecode.Throw;
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

    @Autowired
    AuthenticationService authenticationService;


    public List<RecurrentOrder> getRecurrentActiveOrderToDay(){
        return this.recurrentOrderRepository.findByNextDateAndActive(LocalDate.now(),true);
    }

    public List<PurchaseOrderDTO> processRecurrentOrder(){

        List<PurchaseOrderDTO> purchaseOrderDTOList = new ArrayList<>();
        List<RecurrentOrder>  recurrentActiveOrderToDay = this.getRecurrentActiveOrderToDay();
        for(RecurrentOrder r : recurrentActiveOrderToDay){
            purchaseOrderDTOList.add(this.purchaseOrderService.order(r.getPurchaseOrder()));
            r.setNextDate(r.getNextDate().plusDays(r.getPeriod()));
            this.recurrentOrderRepository.save(r);
        }
        return  purchaseOrderDTOList;
    }

    public List<RecurrentOrder> listAll(){
        return  this.recurrentOrderRepository.findAll();
    }

    public RecurrentOrder create(RecurrentOrderDTO recurrentOrderDTO){

        PurchaseOrder purchaseOrder = this.purchaseOrderService.findById(recurrentOrderDTO.getPurchaseOrder());

        User user = this.authenticationService.authenticated();

        if (purchaseOrder.getClient().getId().equals(user.getId())) {
            RecurrentOrder recurrentOrder = this.convertToEntity(recurrentOrderDTO);
            recurrentOrder.setNextDate(LocalDate.now().plusDays(recurrentOrderDTO.getPeriod()));
            return this.recurrentOrderRepository.save(recurrentOrder);
        }
         throw new RuntimeException("Pedido não pertencente ao usuário logado");
    }

    public RecurrentOrder convertToEntity(RecurrentOrderDTO recurrentOrderDTO){

        return RecurrentOrder
                .builder()
                .id(recurrentOrderDTO.getId())
                .purchaseOrder(this.purchaseOrderService.findById(recurrentOrderDTO.getPurchaseOrder()))
                .period(recurrentOrderDTO.getPeriod())
                .nextDate(LocalDate.now().plusDays(recurrentOrderDTO.getPeriod()))
                .active(true)
                .build();
    }

    public RecurrentOrderDTO convertToDTO(RecurrentOrder recurrentOrder){
        return RecurrentOrderDTO
                .builder()
                .id(recurrentOrder.getId())
                .purchaseOrder(recurrentOrder.getPurchaseOrder().getId())
                .period(recurrentOrder.getPeriod())
                .build();
    }

}
