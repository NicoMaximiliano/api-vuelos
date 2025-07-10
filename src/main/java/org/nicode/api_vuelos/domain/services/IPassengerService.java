package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.Passenger;

import java.util.List;
import java.util.Map;

public interface IPassengerService {

//    List<Passenger> getAll();
    List<Map<String, Object>> getAll();
    Passenger getById(String id);
    Passenger getByFullName(String name, String lastName);
    Passenger getByPassport(String passport);
    void create(Passenger passengerDto);
    void update(String id, Passenger passengerDto);
    void delete(String id);
}
