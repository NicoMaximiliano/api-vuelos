package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.Airline;

import java.util.List;
import java.util.Optional;

public interface IAirlineRepository {

    List<Airline> getAll();
    Optional<Airline> getById(int id);
    Optional<Airline> getByName(String name);
    void save(Airline airlineDto);
    void delete(int id);
    boolean exist(int id);

}
