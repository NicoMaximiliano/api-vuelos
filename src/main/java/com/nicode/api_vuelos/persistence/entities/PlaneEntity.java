package com.nicode.api_vuelos.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avion")
public class PlaneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private Integer id;

    @Column(name = "modelo")
    private String model;

    @Column(name = "capacidad")
    private Integer capacity;


    @ManyToOne(targetEntity = AirlineEntity.class)
    //@Column(name = "id_aerolinea")
    @JoinColumn(name = "id_aerolinea")
    private AirlineEntity airline;

    /*
    @OneToMany(targetEntity = FlightEntity.class, fetch = FetchType.LAZY, mappedBy = "plane")
    private List<FlightEntity> flights;*/
}
