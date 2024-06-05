package com.nicode.api_vuelos.persistence.repositories.crud;

import com.nicode.api_vuelos.persistence.entities.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerCrudRepository extends JpaRepository<PassengerEntity, Integer> {
    Optional<PassengerEntity> findByNameAndLastName(String name, String lastName);
    Optional<PassengerEntity> findByPassport(String passport);
}
