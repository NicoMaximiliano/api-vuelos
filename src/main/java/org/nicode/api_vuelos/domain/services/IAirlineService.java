package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.Airline;

import java.util.List;


public interface IAirlineService {

    List<Airline> getAll();
    Airline getById(String id);
    Airline getByName(String name);
    void create(Airline airlineDto);
    void update(String id, Airline airlineDto);
    void delete(String id);

}
