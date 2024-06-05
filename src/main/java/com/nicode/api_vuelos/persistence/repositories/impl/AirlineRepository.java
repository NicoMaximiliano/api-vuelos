package com.nicode.api_vuelos.persistence.repositories.impl;

import com.nicode.api_vuelos.domain.dtos.Airline;
import com.nicode.api_vuelos.persistence.repositories.IAirlineRepository;
import com.nicode.api_vuelos.persistence.entities.AirlineEntity;
import com.nicode.api_vuelos.persistence.mappers.AirlineMapper;
import com.nicode.api_vuelos.persistence.repositories.crud.AirlineCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class AirlineRepository implements IAirlineRepository {

    @Autowired
    private AirlineCrudRepository airlineCrudRepository;

    @Autowired
    private AirlineMapper airlineMapper;

    @Override
    public List<Airline> getAll() {
        List<AirlineEntity> airlineEntities = airlineCrudRepository.findAll();
        return airlineMapper.toAirlines(airlineEntities);
    }

    @Override
    public Optional<Airline> getById(int id) {
        return airlineCrudRepository.findById(id)
                .map(airlineEntity -> airlineMapper.toAirline(airlineEntity));
    }

    @Override
    public Optional<Airline> getByName(String name) {
        return airlineCrudRepository.findByName(name)
                .map(airlineEntity -> airlineMapper.toAirline(airlineEntity));
    }

    @Override
    public Airline save(Airline airline) {
        AirlineEntity airlineEntity = airlineMapper.toAirlineEntity(airline);
        return airlineMapper.toAirline(airlineCrudRepository.save(airlineEntity));
    }

    @Override
    public void delete(int id) {
        airlineCrudRepository.deleteById(id);
    }

}
