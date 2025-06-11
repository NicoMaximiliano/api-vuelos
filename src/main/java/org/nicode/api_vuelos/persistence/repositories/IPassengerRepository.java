package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.Passenger;

import java.util.List;
import java.util.Optional;

public interface IPassengerRepository {

    List<Passenger> getAll();
    Optional<Passenger> getById(int id);
    Optional<Passenger> getByFullName(String name, String lastName);
    Optional<Passenger> getByPassport(String passport);
    void save(Passenger passengerDto);
    void delete(int id);
    Optional<List<Passenger>> getAllByFlightId(Integer id);
}
