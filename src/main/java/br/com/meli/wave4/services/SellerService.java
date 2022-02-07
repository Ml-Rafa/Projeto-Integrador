package br.com.meli.wave4.services;

import br.com.meli.wave4.DTO.ProductDTO;
import br.com.meli.wave4.DTO.SellerDTO;
import br.com.meli.wave4.DTO.UserDTO;
import br.com.meli.wave4.entities.*;
//import br.com.meli.wave4.entities.Seller;
import br.com.meli.wave4.exceptions.ProductDoesNotBelongToTheSellerException;
//import br.com.meli.wave4.repositories.SellerRepository;
import br.com.meli.wave4.repositories.UserRepository;
import br.com.meli.wave4.services.iservices.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SellerService implements ISellerService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    ProductService productService;

    @Override
    public Boolean verifyProductOfSeller(User seller, Integer productId){
        List<Product> productList = seller.getProductList().stream().filter(p -> p.getId().equals(productId)).collect(Collectors.toList());
        if(productList.isEmpty())
            throw new ProductDoesNotBelongToTheSellerException();
        return true;
    }

    public SellerDTO convertToDTO(User user) {
//        List<ProductDTO> productDTOList = user.getProductList().stream()
//                .map(product -> productService.convertToDTO(product)).collect(Collectors.toList());
        return SellerDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
//                .productDTOList(productDTOList)
                .build();
    }

    public Boolean productBelongToTheSeller(InboundOrder inboundOrder, Batch batch){
        if(inboundOrder.getSellerId().equals(batch.getProduct().getSeller().getId()))
            return true;
        throw new ProductDoesNotBelongToTheSellerException();
    }

    public User convertToEntity(SellerDTO user) {
//        List<Product> productList = user.getProductDTOList()
//                .stream().map(productService::convertToEntity)
//                .collect(Collectors.toList());
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
//                .productList(productList)
                .build();
    }
}
