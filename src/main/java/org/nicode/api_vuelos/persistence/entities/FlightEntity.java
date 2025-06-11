package org.nicode.api_vuelos.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flight")
    private List<PassengerEntity> passengers;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;
}
