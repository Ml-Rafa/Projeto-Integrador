package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sectionCode;
    private Double temperature;
    private Double maxCapacity;
    private Double availableCapacity;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    @OneToMany(mappedBy = "section")
    private List<InboundOrder> inboundOrderList = new ArrayList<>();

    public static void findByCode(Integer sectionCode) {
    }
}
