package com.nicode.api_vuelos.persistence.repositories.impl;

import com.nicode.api_vuelos.domain.dtos.AirportDestiny;
import com.nicode.api_vuelos.domain.dtos.AirportOrigin;
import com.nicode.api_vuelos.persistence.repositories.IAirportRepository;
import com.nicode.api_vuelos.persistence.entities.AirportDestinyEntity;
import com.nicode.api_vuelos.persistence.entities.AirportOriginEntity;
import com.nicode.api_vuelos.persistence.mappers.AirportMapper;
import com.nicode.api_vuelos.persistence.repositories.crud.AirportDestinyCrudRepository;
import com.nicode.api_vuelos.persistence.repositories.crud.AirportOriginCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AirportRepository implements IAirportRepository {

    @Autowired
    private AirportDestinyCrudRepository destinyCrudRepository;
    @Autowired
    private AirportOriginCrudRepository originCrudRepository;

    @Autowired
    private AirportMapper airportMapper;


    @Override
    public List<AirportDestiny> getAllDestiny() {
        List<AirportDestinyEntity> airportsEntities = destinyCrudRepository.findAll();

        return airportMapper.toAirportsDestinies(airportsEntities);
    }

    @Override
    public Optional<AirportDestiny> getDestinyById(int id) {
        return destinyCrudRepository.findById(id).map(airportDestinyEntity -> airportMapper.toAirportDestiny(airportDestinyEntity));
    }

    @Override
    public Optional<AirportDestiny> getDestinyByName(String name) {
        return destinyCrudRepository.findByName(name).map(airportDestinyEntity -> airportMapper.toAirportDestiny(airportDestinyEntity));
    }

    @Override
    public List<AirportDestiny> getAllDestinyByCountry(String country) {
        List<AirportDestinyEntity> airportDestinies = destinyCrudRepository.findAllByCountry(country);

        return airportMapper.toAirportsDestinies(airportDestinies);
    }

    @Override
    public AirportDestiny saveDestiny(AirportDestiny destiny) {
        AirportDestinyEntity destinyEntity = airportMapper.toAirportDestinyEntity(destiny);
        return airportMapper.toAirportDestiny(destinyCrudRepository.save(destinyEntity));
    }

    @Override
    public void deleteDestiny(int id) {
        destinyCrudRepository.deleteById(id);
    }




    @Override
    public List<AirportOrigin> getAllOrigin() {
        List<AirportOriginEntity> airportsEntities = originCrudRepository.findAll();

        return airportMapper.toAirportsOrigins(airportsEntities);
    }

    @Override
    public Optional<AirportOrigin> getOriginById(int id) {
        return originCrudRepository.findById(id).map(airportOriginEntity -> airportMapper.toAirportOrigin(airportOriginEntity));
    }

    @Override
    public Optional<AirportOrigin> getOriginByName(String name) {
        return originCrudRepository.findByName(name).map(airportOriginEntity -> airportMapper.toAirportOrigin(airportOriginEntity));
    }

    @Override
    public List<AirportOrigin> getAllOriginByCountry(String country) {
        List<AirportOriginEntity> airportOrigins = originCrudRepository.findAllByCountry(country);

        return airportMapper.toAirportsOrigins(airportOrigins);
    }

    @Override
    public AirportOrigin saveOrigin(AirportOrigin origin) {
        AirportOriginEntity originEntity = airportMapper.toAirportOriginEntity(origin);

        return airportMapper.toAirportOrigin(originCrudRepository.save(originEntity));
    }

    @Override
    public void deleteOrigin(int id) {
        originCrudRepository.deleteById(id);
    }

}
