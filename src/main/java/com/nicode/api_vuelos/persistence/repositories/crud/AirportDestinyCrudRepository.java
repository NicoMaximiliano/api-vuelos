package com.nicode.api_vuelos.persistence.repositories.crud;

import com.nicode.api_vuelos.persistence.entities.AirportDestinyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportDestinyCrudRepository extends JpaRepository<AirportDestinyEntity, Integer> {

    Optional<AirportDestinyEntity> findByName(String name);
    List<AirportDestinyEntity> findAllByCountry(String country);
}
