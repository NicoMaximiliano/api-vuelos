package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportOrigin;
import org.nicode.api_vuelos.persistence.entities.AirportOriginEntity;
import org.nicode.api_vuelos.util.mappers.AirportMapper;
import org.nicode.api_vuelos.persistence.repositories.IAirportOriginRepository;
import org.nicode.api_vuelos.persistence.repositories.crud.AirportOriginCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirportOriginRepository implements IAirportOriginRepository {

    private final AirportOriginCrudRepository originCrudRepository;
    private final AirportMapper airportOriginMapper;


    @Override
    public List<AirportOrigin> getAll() {
        List<AirportOriginEntity> airportsEntities = originCrudRepository.findAll();
        return airportOriginMapper.toAirportsOrigins(airportsEntities);
    }

    @Override
    public List<AirportOrigin> getAllByCountry(String country) {
        List<AirportOriginEntity> airportOrigins = originCrudRepository.findAllByCountry(country);
        return airportOriginMapper.toAirportsOrigins(airportOrigins);
    }

    @Override
    public Optional<AirportOrigin> getById(int id) {
        return originCrudRepository.findById(id)
                .map(airportOriginMapper::toAirportOrigin);
    }

    @Override
    public Optional<AirportOrigin> getByName(String name) {
        return originCrudRepository.findByName(name)
                .map(airportOriginMapper::toAirportOrigin);
    }

    @Override
    public void save(AirportOrigin airportDto) {
        AirportOriginEntity airportEntity = airportOriginMapper.toAirportOriginEntity(airportDto);
        originCrudRepository.save(airportEntity);
        //return airportOriginMapper.toAirportOrigin(originCrudRepository.save(originEntity));
    }

    @Override
    public void delete(int id) {
        originCrudRepository.deleteById(id);
    }

    @Override
    public boolean exist(int id) {
        return originCrudRepository.existsById(id);
    }
}
