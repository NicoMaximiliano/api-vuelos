package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportDestiny;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportNotFoundException;
import org.nicode.api_vuelos.domain.services.IAirportDestinyService;
import org.nicode.api_vuelos.persistence.repositories.IAirportDestinyRepository;
import org.nicode.api_vuelos.util.converters.EndpointConverter;
import org.nicode.api_vuelos.util.converters.IdConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportDestinyService implements IAirportDestinyService {

    private final IAirportDestinyRepository airportRepository;


    public List<AirportDestiny> getAll(){
        return airportRepository.getAll();
    }

    public List<AirportDestiny> getAllByCountry(String country){
        String nameCountry = EndpointConverter.transformEndpoint(country);;

        if (airportRepository.getAllByCountry(country).isEmpty()){
            throw new AirportNotFoundException("The destination airports not found");
        }
        return airportRepository.getAllByCountry(nameCountry);
    }

    public AirportDestiny getById(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        return airportRepository.getById(optId
                        .orElseThrow(() -> new AirportBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new AirportNotFoundException("The airport with ID " + optId.get() + " was not found"));
    }

    public AirportDestiny getByName(String name){
        String nameAirport = EndpointConverter.transformEndpoint(name);

        return airportRepository.getByName(nameAirport)
                .orElseThrow(()-> new AirportNotFoundException("The airport " + nameAirport + " was not found"));
    }

    public void create(AirportDestiny airportDto){
        airportRepository.save(airportDto);
    }

    public void update(String id, AirportDestiny airportDto){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        AirportDestiny airportToUpdate = airportRepository.getById(optId
                .orElseThrow(() -> new AirportBadRequestException("The ID is an incorrect value")))
                .map(airport -> {
                    if (airportDto.getName() != null){
                        airport.setName(airportDto.getName());
                    }
                    if (airportDto.getCity() != null){
                        airport.setCity(airportDto.getCity());
                    }
                    if (airportDto.getCountry() != null){
                        airport.setCountry(airportDto.getCountry());
                    }

                    return airport;
                })
                .orElseThrow(() -> new AirportNotFoundException("The airport with ID " + optId.get() + " was not found"));

        airportRepository.save(airportToUpdate);
    }

    public void delete(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        AirportDestiny airportToDelete = airportRepository.getById(optId
                .orElseThrow(() -> new AirportBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new AirportNotFoundException("The airport with ID " + optId.get() + " was not found"));

        airportRepository.delete(airportToDelete.getId());
    }

}
