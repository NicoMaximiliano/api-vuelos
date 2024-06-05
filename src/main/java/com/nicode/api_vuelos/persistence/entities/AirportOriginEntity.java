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
@Table(name = "aeropuerto_origen")
public class AirportOriginEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeropuerto")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "ciudad")
    private String city;

    @Column(name = "pais")
    private String country;

    /*
    @OneToMany(targetEntity = FlightEntity.class, fetch = FetchType.LAZY, mappedBy = "origin")
    private List<FlightEntity> flights;*/

}
