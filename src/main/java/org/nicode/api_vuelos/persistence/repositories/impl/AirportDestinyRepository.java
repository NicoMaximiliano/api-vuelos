package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportDestiny;
import org.nicode.api_vuelos.util.mappers.AirportMapper;
import org.nicode.api_vuelos.persistence.repositories.IAirportDestinyRepository;
import org.nicode.api_vuelos.persistence.entities.AirportDestinyEntity;
import org.nicode.api_vuelos.persistence.repositories.crud.AirportDestinyCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirportDestinyRepository implements IAirportDestinyRepository {

    private final AirportDestinyCrudRepository destinyCrudRepository;
    private final AirportMapper airportDestinyMapper;


    @Override
    public List<AirportDestiny> getAll() {
        List<AirportDestinyEntity> airportsEntities = destinyCrudRepository.findAll();
        return airportDestinyMapper.toAirportsDestinies(airportsEntities);
    }

    @Override
    public List<AirportDestiny> getAllByCountry(String country) {
        List<AirportDestinyEntity> airportDestinies = destinyCrudRepository.findAllByCountry(country);
        return airportDestinyMapper.toAirportsDestinies(airportDestinies);
    }

    @Override
    public Optional<AirportDestiny> getById(int id) {
        return destinyCrudRepository.findById(id).map(airportDestinyMapper::toAirportDestiny);
    }

    @Override
    public Optional<AirportDestiny> getByName(String name) {
        return destinyCrudRepository.findByName(name).map(airportDestinyMapper::toAirportDestiny);
    }

    @Override
    public void save(AirportDestiny airportDto) {
        AirportDestinyEntity airportEntity = airportDestinyMapper.toAirportDestinyEntity(airportDto);
        destinyCrudRepository.save(airportEntity);
        //return airportDestinyMapper.toAirportDestiny(destinyCrudRepository.save(destinyEntity));
    }

    @Override
    public void delete(int id) {
        destinyCrudRepository.deleteById(id);
    }

    @Override
    public boolean exist(int id) {
        return destinyCrudRepository.existsById(id);
    }
}
