package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Airline;
import org.nicode.api_vuelos.persistence.repositories.IAirlineRepository;
import org.nicode.api_vuelos.persistence.entities.AirlineEntity;
import org.nicode.api_vuelos.util.mappers.AirlineMapper;
import org.nicode.api_vuelos.persistence.repositories.crud.AirlineCrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AirlineRepository implements IAirlineRepository {

    private final AirlineCrudRepository airlineCrudRepository;
    private final AirlineMapper airlineMapper;


    @Override
    public List<Airline> getAll() {
        List<AirlineEntity> airlineEntities = airlineCrudRepository.findAll(); //OBTENGO LOS ENTITIES
        return airlineMapper.toAirlines(airlineEntities); //LOS TRANSFORMO EN DTO´S Y LOS DEVUELVO
    }

    @Override
    public Optional<Airline> getById(int id) {
        return airlineCrudRepository.findById(id) //OBTENGO EL ENTITY
                .map(airlineMapper::toAirline); //LO TRANSFORMO EN UN DTO Y LO RETORNO
    }

    @Override
    public Optional<Airline> getByName(String name) {
        return airlineCrudRepository.findByName(name) //OBTENGO EL ENTITY
                .map(airlineMapper::toAirline); //LO TRANSFORMO EN UN DTO Y LO RETORNO
    }

    @Override
    public void save(Airline airlineDto) {
        AirlineEntity airlineEntity = airlineMapper.toAirlineEntity(airlineDto); //TRANSFORMO EL DTO EN UN ENTITY
        airlineCrudRepository.save(airlineEntity); //GUARDO EL ENTITY EN LA BD
        //return airlineMapper.toAirline(airlineCrudRepository.save(airlineEntity)); //GUARDO EL ENTITY EN LA BD, LO TRANSFORMO EN UN DTO Y LO DEVUELVO
    }

    @Override
    public void delete(int id) {
        airlineCrudRepository.deleteById(id);
    }

    @Override
    public boolean exist(int id) {
        return airlineCrudRepository.existsById(id);
    }

}
