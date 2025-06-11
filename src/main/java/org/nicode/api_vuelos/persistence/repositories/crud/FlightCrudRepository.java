package org.nicode.api_vuelos.persistence.repositories.crud;

import org.nicode.api_vuelos.persistence.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightCrudRepository extends JpaRepository<FlightEntity, Integer> {

    @Override
    boolean existsById(Integer integer);
}
