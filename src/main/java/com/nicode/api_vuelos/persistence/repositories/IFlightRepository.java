package com.nicode.api_vuelos.persistence.repositories;

import com.nicode.api_vuelos.domain.dtos.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightRepository {

    List<Flight> getAll();
    Optional<Flight> getById(int id);
    Flight save(Flight flight);
    void delete(int id);
}
