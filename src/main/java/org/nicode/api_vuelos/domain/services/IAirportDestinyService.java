package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.AirportDestiny;

import java.util.List;

public interface IAirportDestinyService {

    List<AirportDestiny> getAll();
    List<AirportDestiny> getAllByCountry(String country);
    AirportDestiny getById(String id);
    AirportDestiny getByName(String name);
    void create(AirportDestiny airportDto);
    void update(String id, AirportDestiny airportDto);
    void delete(String id);
}
