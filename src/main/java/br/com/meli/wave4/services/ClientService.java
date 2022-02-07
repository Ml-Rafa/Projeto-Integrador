package br.com.meli.wave4.services;

//import br.com.meli.wave4.entities.Client;
import br.com.meli.wave4.DTO.ClientDTO;
import br.com.meli.wave4.DTO.PurchaseOrderDTO;
import br.com.meli.wave4.entities.User;
//import br.com.meli.wave4.repositories.ClientRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.IClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientService implements IClientService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    public ClientDTO convertToDTO(User user) {

        List<PurchaseOrderDTO> purchaseOrderDTOs = user.getListPurchaseOrder()
                .stream().map(purchaseOrder -> purchaseOrderService.convertToDTO(purchaseOrder))
                .collect(Collectors.toList());
        return ClientDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
                .address(user.getAddress())
                .telephone(user.getTelephone())
                .purchaseOrderDTOList(purchaseOrderDTOs)
                .build();
    }

    @Override
    public User findById(Integer clientId){
        return this.userRepository.findById(clientId).orElse(null);
    }

//    @Autowired
//    ClientRepository clientRepository;
//
//    @Override
//    public Client findById(Integer clientId){
//        return this.clientRepository.findById(clientId).orElse(null);
//    }

}
