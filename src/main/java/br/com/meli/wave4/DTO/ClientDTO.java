package br.com.meli.wave4.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ClientDTO {
    private Integer id;
    private String name;
    private String document;
    private String address;
    private String telephone;
    private List<PurchaseOrderDTO> purchaseOrderDTOList = new ArrayList<>();

}