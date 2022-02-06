package br.com.meli.wave4.DTO;

import br.com.meli.wave4.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class RepresentativeDTO {
    private Integer id;
    private String name;
    private String document;
    private Integer warehouseId;

    public static RepresentativeDTO convertToDTO(User user) {
        return RepresentativeDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .document(user.getDocument())
                .warehouseId(user.getWarehouse().getId())
                .build();
    }

}