package com.nicode.api_vuelos.persistence.repositories.crud;

import com.nicode.api_vuelos.persistence.entities.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaneCrudRepository extends JpaRepository<PlaneEntity, Integer> {

    Optional<PlaneEntity> findByModel(String model);
}
