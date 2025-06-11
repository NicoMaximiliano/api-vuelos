package org.nicode.api_vuelos.persistence.repositories.crud;

import org.nicode.api_vuelos.persistence.entities.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineCrudRepository extends JpaRepository<AirlineEntity, Integer> {
    Optional<AirlineEntity> findByName(String name);

    @Override
    boolean existsById(Integer integer);
}
