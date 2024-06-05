package com.nicode.api_vuelos.persistence.repositories.impl;

import com.nicode.api_vuelos.domain.dtos.Passenger;
import com.nicode.api_vuelos.persistence.repositories.IPassengerRepository;
import com.nicode.api_vuelos.persistence.entities.PassengerEntity;
import com.nicode.api_vuelos.persistence.mappers.PassengerMapper;
import com.nicode.api_vuelos.persistence.repositories.crud.PassengerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepository implements IPassengerRepository {

    @Autowired
    private PassengerCrudRepository passengerCrudRepository;

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public List<Passenger> getAll() {
        List<PassengerEntity> passengerEntities = passengerCrudRepository.findAll();

        return passengerMapper.toPassengers(passengerEntities);
    }

    @Override
    public Optional<Passenger> getById(int id) {
        return passengerCrudRepository.findById(id)
                .map(passengerEntity -> passengerMapper.toPassenger(passengerEntity));
    }

    @Override
    public Optional<Passenger> getByFullName(String name, String lastName) {
        return passengerCrudRepository.findByNameAndLastName(name, lastName)
                .map(passengerEntity -> passengerMapper.toPassenger(passengerEntity));
    }

    @Override
    public Optional<Passenger> getByPassport(String passport) {
        return passengerCrudRepository.findByPassport(passport)
                .map(passengerEntity -> passengerMapper.toPassenger(passengerEntity));
    }

    @Override
    public Passenger save(Passenger passenger) {
        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(passenger);
        return passengerMapper.toPassenger(passengerCrudRepository.save(passengerEntity));
    }

    @Override
    public void delete(int id) {
        passengerCrudRepository.deleteById(id);
    }

}
