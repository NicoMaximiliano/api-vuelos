package com.nicode.api_vuelos.persistence.repositories;

import com.nicode.api_vuelos.domain.dtos.AirportDestiny;
import com.nicode.api_vuelos.domain.dtos.AirportOrigin;

import java.util.List;
import java.util.Optional;

public interface IAirportRepository {
    List<AirportDestiny> getAllDestiny();
    Optional<AirportDestiny> getDestinyById(int id);
    Optional<AirportDestiny> getDestinyByName(String name);
    List<AirportDestiny> getAllDestinyByCountry(String country);
    AirportDestiny saveDestiny(AirportDestiny destiny);
    void deleteDestiny(int id);


    List<AirportOrigin> getAllOrigin();
    Optional<AirportOrigin> getOriginById(int id);
    Optional<AirportOrigin> getOriginByName(String name);
    List<AirportOrigin> getAllOriginByCountry(String country);
    AirportOrigin saveOrigin(AirportOrigin origin);
    void deleteOrigin(int id);
}
