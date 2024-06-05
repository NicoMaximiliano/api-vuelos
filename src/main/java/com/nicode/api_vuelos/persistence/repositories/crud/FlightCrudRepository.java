package com.nicode.api_vuelos.persistence.repositories.crud;

import com.nicode.api_vuelos.persistence.entities.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightCrudRepository extends JpaRepository<FlightEntity, Integer> {
}
