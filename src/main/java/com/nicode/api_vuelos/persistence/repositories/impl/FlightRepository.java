package com.nicode.api_vuelos.persistence.repositories.impl;

import com.nicode.api_vuelos.domain.dtos.Flight;
import com.nicode.api_vuelos.persistence.repositories.IFlightRepository;
import com.nicode.api_vuelos.persistence.entities.FlightEntity;
import com.nicode.api_vuelos.persistence.mappers.FlightMapper;
import com.nicode.api_vuelos.persistence.repositories.crud.FlightCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository implements IFlightRepository {

    @Autowired
    private FlightCrudRepository flightCrudRepository;

    @Autowired
    private FlightMapper flightMapper;

    @Override
    public List<Flight> getAll() {
        List<FlightEntity> flightEntities = flightCrudRepository.findAll();
        return flightMapper.toFlights(flightEntities);
    }

    @Override
    public Optional<Flight> getById(int id) {
        return flightCrudRepository.findById(id)
                .map(flightEntity -> flightMapper.toFlight(flightEntity));
    }

    @Override
    public Flight save(Flight flight) {
        FlightEntity flightEntity = flightMapper.toFlightEntity(flight);
        return flightMapper.toFlight(flightCrudRepository.save(flightEntity));
    }

    @Override
    public void delete(int id) {
        flightCrudRepository.deleteById(id);
    }
}
