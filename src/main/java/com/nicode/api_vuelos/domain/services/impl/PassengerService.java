package com.nicode.api_vuelos.domain.services.impl;

import com.nicode.api_vuelos.domain.dtos.Passenger;
import com.nicode.api_vuelos.persistence.repositories.IPassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private IPassengerRepository passengerRepository;

    public List<Passenger> getAllPassengerService(){
        return passengerRepository.getAll();
    }

    public Optional<Passenger> getPassengerServiceById(int id){
        return passengerRepository.getById(id);
    }

    public Optional<Passenger> getPassengerServiceByFullName(String name, String lastName){
        return passengerRepository.getByFullName(name, lastName);
    }

    /*
    public Optional<Passenger> getPassengerServiceByFullName(Passenger passenger){
        String name = passenger.getName();
        String lastName = passenger.getLastName();

        return passengerRepository.getByFullName(name, lastName);
    }*/

    public Optional<Passenger> getPassengerServiceByPassport(String passport){
        return passengerRepository.getByPassport(passport);
    }

    public Passenger savePassengerService(Passenger passenger){
        return passengerRepository.save(passenger);
    }

    public Optional<Passenger> deletePassengerService(int id){

        return getPassengerServiceById(id)
                .map(passenger -> {
                    passengerRepository.delete(id);
                    return passenger;
                });
    }

}
