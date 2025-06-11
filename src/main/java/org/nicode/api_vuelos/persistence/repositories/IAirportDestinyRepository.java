package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.AirportDestiny;

import java.util.List;
import java.util.Optional;

public interface IAirportDestinyRepository {

    List<AirportDestiny> getAll();
    List<AirportDestiny> getAllByCountry(String country);
    Optional<AirportDestiny> getById(int id);
    Optional<AirportDestiny> getByName(String name);
    void save(AirportDestiny airportDto);
    void delete(int id);
    boolean exist(int id);
}
