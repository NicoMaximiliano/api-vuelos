package com.nicode.api_vuelos.domain.services;

import com.nicode.api_vuelos.domain.dtos.Airline;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IAirlineService {

    List<Airline> getAllAirlineService();
    Optional<Airline> getAirlineServiceById(int id);
    Optional<Airline> getAirlineServiceByName(String name);
    Airline saveAirlineService(Airline airline);
    Optional<Map<?,?>> deleteAirlineService(int id);
}
