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

    @OneToMany(targetEntity = FlightEntity.class, fetch = FetchType.LAZY, mappedBy = "plane")
    private List<FlightEntity> flights;

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
