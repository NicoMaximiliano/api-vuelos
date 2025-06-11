package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.persistence.repositories.IPassengerRepository;
import org.nicode.api_vuelos.persistence.entities.PassengerEntity;
import org.nicode.api_vuelos.util.mappers.PassengerMapper;
import org.nicode.api_vuelos.persistence.repositories.crud.PassengerCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PassengerRepository implements IPassengerRepository {

    private final PassengerCrudRepository passengerCrudRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public List<Passenger> getAll() {
        List<PassengerEntity> passengerEntities = passengerCrudRepository.findAll();
        return passengerMapper.toPassengers(passengerEntities);
    }

    @Override
    public Optional<Passenger> getById(int id) {
        return passengerCrudRepository.findById(id)
                .map(passengerMapper::toPassenger);
    }

    @Override
    public Optional<Passenger> getByFullName(String name, String lastName) {
        return passengerCrudRepository.findByNameAndLastName(name, lastName)
                .map(passengerMapper::toPassenger);
    }

    @Override
    public Optional<Passenger> getByPassport(String passport) {
        return passengerCrudRepository.findByPassport(passport)
                .map(passengerMapper::toPassenger);
    }

    @Override
    public void save(Passenger passengerDto) {
        PassengerEntity passengerEntity = passengerMapper.toPassengerEntity(passengerDto);
        passengerCrudRepository.save(passengerEntity);
        //return passengerMapper.toPassenger(passengerCrudRepository.save(passengerEntity));
    }

    @Override
    public void delete(int id) {
        passengerCrudRepository.deleteById(id);
    }

    @Override
    public Optional<List<Passenger>> getAllByFlightId(Integer id) {
        return passengerCrudRepository.findAllByFlightId(id)
                .map(passengerMapper::toPassengers);
    }

}
