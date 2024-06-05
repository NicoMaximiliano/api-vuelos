package com.nicode.api_vuelos.domain.services.impl;

import com.nicode.api_vuelos.domain.dtos.Flight;
import com.nicode.api_vuelos.persistence.repositories.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private IFlightRepository flightRepository;

    public List<Flight> getAllFlightService(){
        return flightRepository.getAll();
    }

    public Optional<Flight> getFlightService(int id){
        return flightRepository.getById(id);
    }

    public Flight saveFlightService(Flight flight){
        return flightRepository.save(flight);
    }

    public Optional<Flight> deleteFlightService(int id){
        return getFlightService(id)
                .map(flight -> {
                    flightRepository.delete(id);
                    return flight;
                });
    }
}
