package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightRepository {

    List<Flight> getAll();
    Optional<Flight> getById(int id);
    void save(Flight flightDto);
    void delete(int id);
    boolean exist(int id);
}
