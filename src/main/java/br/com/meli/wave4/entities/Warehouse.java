package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "geographic_area")
    private String geographicArea = "";

    @JsonBackReference
    @OneToOne(mappedBy = "warehouse")
    private User representative;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    Set<Section> sectionSet = new HashSet<>();
}
