package br.com.meli.wave4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "sectionCode")
    private Section section;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inboundOrder")
    private List<Batch> batchStock = new ArrayList<>();

    @OneToOne
    private Warehouse warehouse;
}
