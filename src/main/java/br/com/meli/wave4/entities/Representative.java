package br.com.meli.wave4.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Representative extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id",referencedColumnName = "id")
    private Warehouse warehouse;

    @OneToOne(mappedBy = "representative")
    private Stock stock;

    @OneToMany(mappedBy = "representative", cascade = CascadeType.ALL)
    private List<Batch> batch;
}
