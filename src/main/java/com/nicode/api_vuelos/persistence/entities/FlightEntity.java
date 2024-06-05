package com.nicode.api_vuelos.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vuelo")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Integer id;

    @Column(name = "precio")
    private Float price;

    @Column(name = "hora_salida")
    private LocalDateTime start;

    @Column(name = "hora_llegada")
    private LocalDateTime finish;


    @ManyToOne(targetEntity = AirlineEntity.class)
    //@Column(name = "id_aerolinea")
    @JoinColumn(name = "id_aerolinea")
    private AirlineEntity airline;

    @ManyToOne(targetEntity = PlaneEntity.class)
    //@Column(name = "id_avion")
    @JoinColumn(name = "id_avion")
    private PlaneEntity plane;

    @ManyToOne(targetEntity = AirportOriginEntity.class)
    //@Column(name = "id_origen")
    @JoinColumn(name = "id_origen")
    private AirportOriginEntity origin;

    @ManyToOne(targetEntity = AirportDestinyEntity.class)
    //@Column(name = "id_destino")
    @JoinColumn(name = "id_destino")
    private AirportDestinyEntity destiny;

    /*
    @OneToMany(targetEntity = PassengerEntity.class, fetch = FetchType.LAZY, mappedBy = "flight")
    private List<PassengerEntity> passengers;*/
}
