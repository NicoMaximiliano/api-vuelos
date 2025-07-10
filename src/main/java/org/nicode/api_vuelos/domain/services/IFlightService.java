package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.Flight;

import java.util.List;
import java.util.Map;

public interface IFlightService {

    List<Flight> getAll();
    Flight getById(String id);
    void create(Flight flightDto);
    void update(String id, Flight flightDto);
    void delete(String id);
//    List<Passenger> getAllPassengersById(String id);
    List<Map<String, Object>> getAllPassengersById(String id);
}
