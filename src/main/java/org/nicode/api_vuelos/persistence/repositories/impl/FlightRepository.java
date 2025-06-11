package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Flight;
import org.nicode.api_vuelos.persistence.repositories.IFlightRepository;
import org.nicode.api_vuelos.persistence.entities.FlightEntity;
import org.nicode.api_vuelos.util.mappers.FlightMapper;
import org.nicode.api_vuelos.persistence.repositories.crud.FlightCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FlightRepository implements IFlightRepository {

    private final FlightCrudRepository flightCrudRepository;
    private final FlightMapper flightMapper;

    @Override
    public List<Flight> getAll() {
        List<FlightEntity> flightEntities = flightCrudRepository.findAll();
        return flightMapper.toFlights(flightEntities);
    }

    @Override
    public Optional<Flight> getById(int id) {
        return flightCrudRepository.findById(id)
                .map(flightMapper::toFlight);
    }

    @Override
    public void save(Flight flightDto) {
        FlightEntity flightEntity = flightMapper.toFlightEntity(flightDto);
        flightCrudRepository.save(flightEntity);
        //return flightMapper.toFlight(flightCrudRepository.save(flightEntity));
    }

    @Override
    public void delete(int id) {
        flightCrudRepository.deleteById(id);
    }

    @Override
    public boolean exist(int id) {
        return flightCrudRepository.existsById(id);
    }
}
