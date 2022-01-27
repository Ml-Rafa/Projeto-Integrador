package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "geographic_area")
    private String geographicArea;

    @OneToOne(mappedBy = "warehouse")
    private Representative representative;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    Set<Section> sectionSet = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return id.equals(warehouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
