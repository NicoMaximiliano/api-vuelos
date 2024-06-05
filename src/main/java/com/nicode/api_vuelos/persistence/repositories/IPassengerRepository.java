package com.nicode.api_vuelos.persistence.repositories;

import com.nicode.api_vuelos.domain.dtos.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengerRepository {

    List<Passenger> getAll();
    Optional<Passenger> getById(int id);
    Optional<Passenger> getByFullName(String name, String lastName);
    Optional<Passenger> getByPassport(String passport);

    Passenger save(Passenger passenger);
    void delete(int id);
}
