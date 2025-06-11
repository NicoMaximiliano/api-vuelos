package org.nicode.api_vuelos.persistence.repositories.crud;

import org.nicode.api_vuelos.persistence.entities.AirportOriginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportOriginCrudRepository extends JpaRepository<AirportOriginEntity, Integer> {
    Optional<AirportOriginEntity> findByName(String name);
    List<AirportOriginEntity> findAllByCountry(String country);

    @Override
    boolean existsById(Integer integer);
}
