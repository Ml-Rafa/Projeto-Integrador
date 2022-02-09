package br.com.meli.wave4.entities;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "delivery_dates")
@Getter
@Setter
public class DeliveryDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "delivery_location")
    private String deliveryLocation;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "date_is_available")
    private Boolean dateIsAvailable;



}
