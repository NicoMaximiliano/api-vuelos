package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.AirportOrigin;

import java.util.List;
import java.util.Optional;

public interface IAirportOriginRepository {

    List<AirportOrigin> getAll();
    List<AirportOrigin> getAllByCountry(String country);
    Optional<AirportOrigin> getById(int id);
    Optional<AirportOrigin> getByName(String name);
    void save(AirportOrigin airportDto);
    void delete(int id);
    boolean exist(int id);
}
