package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.AirportOrigin;

import java.util.List;

public interface IAirportOriginService {

    List<AirportOrigin> getAll();
    List<AirportOrigin> getAllByCountry(String country);
    AirportOrigin getById(String id);
    AirportOrigin getByName(String name);
    void create(AirportOrigin airportDto);
    void update(String id, AirportOrigin airportDto);
    void delete(String id);

}
