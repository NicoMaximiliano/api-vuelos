package com.nicode.api_vuelos.domain.services.impl;

import com.nicode.api_vuelos.domain.dtos.Airline;
import com.nicode.api_vuelos.domain.services.IAirlineService;
import com.nicode.api_vuelos.persistence.repositories.IAirlineRepository;
import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.web.exceptions.airline_exception.AirlineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AirlineService implements IAirlineService {

    @Autowired
    private IAirlineRepository airlineRepository;

    @Autowired
    private Response response;

    public List<Airline> getAllAirlineService(){
        return airlineRepository.getAll();
    }

    public Optional<Airline> getAirlineServiceById(int id){
        return Optional.ofNullable(airlineRepository.getById(id)
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea con id " + id + " no fue encontrada")));
    }

    /*
    public Optional<Airline> getAirlineServiceById(int id){
        return airlineRepository.getById(id);
    }*/

    public Optional<Airline> getAirlineServiceByName(String name){
        String nameAirline;

        if (name.contains("-")){
            nameAirline = name.replace("-"," ");
        }
        else{
            nameAirline = name;
        }

        return Optional.ofNullable(airlineRepository.getByName(nameAirline)
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea " + name + " no fue encontrada")));
    }

    /*
    public Optional<Airline> getAirlineServiceByName(String name){
        String nameAirline;

        if (name.contains("-")){
            nameAirline = name.replace("-"," ");
        }
        else{
            nameAirline = name;
        }

        return airlineRepository.getByName(nameAirline);
    }*/

    public Airline saveAirlineService(Airline airline){
        return airlineRepository.save(airline);
    }

    public Optional<Map<?,?>> deleteAirlineService(int id){
        return Optional.ofNullable(getAirlineServiceById(id)
                .map(airline -> {
                    Map<?,?> body = response.createBody("La aerolinea con id "+ id +" ha sido eliminada", HttpStatus.OK.toString());
                    airlineRepository.delete(id);
                    return body;
                })
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea con id " + id + " no fue encontrada")));
    }

    /*
    public Optional<Airline> deleteAirlineService(int id){
        return getAirlineServiceById(id)
                .map(airline -> {
                    airlineRepository.delete(id);
                    return airline;
                });
    }*/

}
