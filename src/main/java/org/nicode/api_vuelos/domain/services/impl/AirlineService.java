package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Airline;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineNotFoundException;
import org.nicode.api_vuelos.domain.services.IAirlineService;
import org.nicode.api_vuelos.persistence.repositories.IAirlineRepository;
import org.nicode.api_vuelos.util.converters.EndpointConverter;
import org.nicode.api_vuelos.util.converters.IdConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService implements IAirlineService {

    private final IAirlineRepository airlineRepository;


    public List<Airline> getAll(){
        return airlineRepository.getAll();
    }

    public Airline getById(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        return airlineRepository.getById(optId
                        .orElseThrow(() -> new AirlineBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new AirlineNotFoundException("The airline with ID " + optId.get() + " was not found"));
    }

    public Airline getByName(String name){
        String nameAirline = EndpointConverter.transformEndpoint(name);

        return airlineRepository.getByName(nameAirline)
                .orElseThrow(() -> new AirlineNotFoundException("The airline " + nameAirline + " was not found"));
    }

    public void create(Airline airlineDto){
        airlineRepository.save(airlineDto);
    }

    public void update(String id, Airline airlineDto){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Airline airlineToUpdate = airlineRepository.getById(optId
                        .orElseThrow(() -> new AirlineBadRequestException("The ID is an incorrect value")))
                .map(airline -> {
                    if (airlineDto.getName() != null){
                        airline.setName(airlineDto.getName());
                    }
                    if (airlineDto.getCountry() != null){
                        airline.setCountry(airlineDto.getCountry());
                    }
                    return airline;
                })
                .orElseThrow(() -> new AirlineNotFoundException("The airline with ID " + optId.get() + " was not found"));

        airlineRepository.save(airlineToUpdate);
    }

    public void delete(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Airline airlineToDelete = airlineRepository.getById(optId
                        .orElseThrow(() -> new AirlineBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new AirlineNotFoundException("The airline with ID " + optId.get() + " was not found"));

        airlineRepository.delete(airlineToDelete.getId());
    }


}
