package com.nicode.api_vuelos.domain.services.impl;

import com.nicode.api_vuelos.domain.dtos.AirportDestiny;
import com.nicode.api_vuelos.domain.dtos.AirportOrigin;
import com.nicode.api_vuelos.persistence.repositories.IAirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private IAirportRepository airportRepository;

    public List<AirportDestiny> getAllDestinyService(){
        return airportRepository.getAllDestiny();
    }

    public Optional<AirportDestiny> getDestinyServiceById(int id){
        return airportRepository.getDestinyById(id);
    }

    public Optional<AirportDestiny> getDestinyServiceByName(String name){
        String nameDestiny;

        if (name.contains("-")){
            nameDestiny = name.replace("-"," ");
        }
        else{
            nameDestiny = name;
        }

        return airportRepository.getDestinyByName(nameDestiny);
    }

    public List<AirportDestiny> getAllDestinyServiceByCountry(String country){
        String nameCountry;

        if (country.contains("-")){
            nameCountry = country.replace("-"," ");
        }
        else{
            nameCountry = country;
        }
        return airportRepository.getAllDestinyByCountry(nameCountry);
    }

    public AirportDestiny saveAirportDestinyService(AirportDestiny destiny){
        return airportRepository.saveDestiny(destiny);
    }

    public Optional<AirportDestiny> deleteAirportDestinyService(int id){
        return getDestinyServiceById(id)
                .map(airportDestiny -> {
                    airportRepository.deleteDestiny(id);
                    return airportDestiny;
                });
    }




    public List<AirportOrigin> getAllOriginService(){
        return airportRepository.getAllOrigin();
    }

    public Optional<AirportOrigin> getOriginServiceById(int id){
        return airportRepository.getOriginById(id);
    }

    public Optional<AirportOrigin> getOriginServiceByName(String name){
        String nameOrigin;

        if (name.contains("-")){
            nameOrigin = name.replace("-"," ");
        }
        else{
            nameOrigin = name;
        }

        return airportRepository.getOriginByName(nameOrigin);
    }

    public List<AirportOrigin> getAllOriginServiceByCountry(String country){
        String nameCountry;

        if (country.contains("-")){
            nameCountry = country.replace("-"," ");
        }
        else{
            nameCountry = country;
        }
        return airportRepository.getAllOriginByCountry(nameCountry);
    }

    public AirportOrigin saveAirportOriginService(AirportOrigin origin){
        return airportRepository.saveOrigin(origin);
    }

    public Optional<AirportOrigin> deleteAirportOriginService(int id){
        return getOriginServiceById(id)
                .map(airportOrigin -> {
                    airportRepository.deleteOrigin(id);
                    return airportOrigin;
                });
    }
}
