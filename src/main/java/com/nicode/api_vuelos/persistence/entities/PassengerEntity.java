package com.nicode.api_vuelos.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pasajero")
public class PassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "pasaporte")
    private String passport;

    @Column(name = "nacionalidad")
    private String nationality;

    @ManyToOne(targetEntity = FlightEntity.class)
    //@Column(name = "id_vuelo")
    @JoinColumn(name = "id_vuelo")
    private FlightEntity flight;
}
