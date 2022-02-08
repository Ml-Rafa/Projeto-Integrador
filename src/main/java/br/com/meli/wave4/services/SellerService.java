package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.SellerDTO;
import br.com.meli.wave4.entities.Batch;
import br.com.meli.wave4.entities.InboundOrder;
import br.com.meli.wave4.entities.Product;
import br.com.meli.wave4.entities.User;
import br.com.meli.wave4.exceptions.ProductDoesNotBelongToTheSellerException;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService implements ISellerService {

    @Autowired
    UserRepository userRepository;



    @Override
    public Boolean verifyProductOfSeller(User seller, Integer productId){
        List<Product> productList = seller.getProductList().stream().filter(p -> p.getId().equals(productId)).collect(Collectors.toList());
        if(productList.isEmpty())
            throw new ProductDoesNotBelongToTheSellerException();
        return true;
    }

    public SellerDTO convertToDTO(User user) {
     return SellerDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
                .build();
    }

    public Boolean productBelongToTheSeller(InboundOrder inboundOrder, Batch batch){
        if(inboundOrder.getSellerId().equals(batch.getProduct().getSeller().getId()))
            return true;
        throw new ProductDoesNotBelongToTheSellerException();
    }

    public User convertToEntity(SellerDTO user) {

        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
                .build();
    }
}
