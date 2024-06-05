package com.nicode.api_vuelos.persistence.repositories;

import com.nicode.api_vuelos.domain.dtos.Airline;

import java.util.List;
import java.util.Optional;

public interface IAirlineRepository {

    List<Airline> getAll();
    Optional<Airline> getById(int id);
    Optional<Airline> getByName(String name);
    Airline save(Airline airline);

    void delete(int id);
}
