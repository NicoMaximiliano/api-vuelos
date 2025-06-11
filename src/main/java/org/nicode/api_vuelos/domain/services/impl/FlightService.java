package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Flight;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneNotFoundException;
import org.nicode.api_vuelos.domain.services.IFlightService;
import org.nicode.api_vuelos.persistence.repositories.*;
import org.nicode.api_vuelos.util.converters.IdConverter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FlightService implements IFlightService {

    private final IFlightRepository flightRepository;
    private final IPassengerRepository passengerRepository;
    private final IAirportDestinyRepository destinyRepository;
    private final IAirportOriginRepository originRepository;
    private final IPlaneRepository planeRepository;
    private final IAirlineRepository airlineRepository;


    public List<Flight> getAll(){
        return flightRepository.getAll();
    }

    public Flight getById(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        return flightRepository.getById(optId
                        .orElseThrow(() -> new  FlightBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new FlightNotFoundException("The flight with ID " + optId.get() + " was not found"));
    }

    public void create(Flight flightDto){
        if (validatePlane(flightDto) && validateAirportOrigin(flightDto) && validateAirportDestiny(flightDto)) {
            flightRepository.save(flightDto);
        }
        else{
            throw new FlightBadRequestException("The flight cannot be created because the plane, origin or destiny airport is invalid");
        }
    }

    @Override
    public void update(String id, Flight flightDto) {
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Flight flightToUpdate = flightRepository.getById(optId
                        .orElseThrow(() -> new FlightBadRequestException("The ID is an incorrect value")))
                .map(flight -> {
                    if (flightDto.getPrice() != null) {
                        flight.setPrice(flightDto.getPrice());
                    }
                    if (flightDto.getStart() != null) {
                        flight.setStart(flightDto.getStart());
                    }
                    if (flightDto.getFinish() != null) {
                        flight.setFinish(flightDto.getFinish());
                    }

                    return flight;
                })
                .orElseThrow(() -> new FlightNotFoundException("The flight with ID " + optId.get() + " was not found"));

        flightRepository.save(flightToUpdate);
    }

    public boolean validatePlane(Flight flightDto) {
        boolean isValid = false;

        if(planeRepository.exist(flightDto.getPlane().getId())){
            String model = planeRepository.getById(flightDto.getPlane().getId()).get().getModel();
            Integer capacity = planeRepository.getById(flightDto.getPlane().getId()).get().getCapacity();

            if (flightDto.getPlane().getModel() != null && flightDto.getPlane().getCapacity() != null) {
                if(flightDto.getPlane().getModel().equalsIgnoreCase(model) && flightDto.getPlane().getCapacity().equals(capacity)){
                    isValid = true;
                }
                else {
                    throw new PlaneBadRequestException("The plane fields do not match the provided model or capacity");
                }
            }
            else{
                throw new PlaneBadRequestException("Plane fields cannot be null");
            }
        }
        else {
            throw new PlaneNotFoundException("The plane with ID " + flightDto.getPlane().getId() + " does not exist");
        }

        return isValid && validateAirline(flightDto);
    }

    public boolean validateAirline(Flight flightDto) {
        boolean isValid = false;

        if (airlineRepository.exist(flightDto.getPlane().getAirline().getId())) {
            String name = airlineRepository.getById(flightDto.getPlane().getAirline().getId()).get().getName();
            String country = airlineRepository.getById(flightDto.getPlane().getAirline().getId()).get().getCountry();

            if (flightDto.getPlane().getAirline().getName() != null && flightDto.getPlane().getAirline().getCountry() != null) {
                if (flightDto.getPlane().getAirline().getName().equalsIgnoreCase(name) && flightDto.getPlane().getAirline().getCountry().equalsIgnoreCase(country)) {
                    isValid = true;
                } else {
                    throw new AirlineBadRequestException("The airline fields do not match the provided name or country");
                }
            } else {
                throw new AirlineBadRequestException("Airline fields cannot be null");
            }
        } else {
            throw new AirlineNotFoundException("The airline with ID " + flightDto.getPlane().getAirline().getId() + " does not exist");
        }

        return isValid;
    }

    public boolean validateAirportOrigin(Flight flightDto) {
        boolean isValid = false;

        if (originRepository.exist(flightDto.getOrigin().getId())) {
            String name = originRepository.getById(flightDto.getOrigin().getId()).get().getName();
            String city = originRepository.getById(flightDto.getOrigin().getId()).get().getCity();
            String country = originRepository.getById(flightDto.getOrigin().getId()).get().getCountry();

            if (flightDto.getOrigin().getName() != null && flightDto.getOrigin().getCity() != null && flightDto.getOrigin().getCountry() != null) {
                if(flightDto.getOrigin().getName().equalsIgnoreCase(name) && flightDto.getOrigin().getCity().equalsIgnoreCase(city) && flightDto.getOrigin().getCountry().equalsIgnoreCase(country)){
                    isValid = true;
                }
                else {
                    throw new AirportBadRequestException("The origin airport fields do not match the provided name, city, or country");
                }
            }
            else{
                throw new AirportBadRequestException("Origin airport fields cannot be null");
            }
        }
        else {
            throw new AirportNotFoundException("The origin airport with ID " + flightDto.getOrigin().getId() + " does not exist");
        }

        return isValid;
    }

    public boolean validateAirportDestiny(Flight flightDto) {
        boolean isValid = false;

        if (destinyRepository.exist(flightDto.getDestiny().getId())) {
            String name = destinyRepository.getById(flightDto.getDestiny().getId()).get().getName();
            String city = destinyRepository.getById(flightDto.getDestiny().getId()).get().getCity();
            String country = destinyRepository.getById(flightDto.getDestiny().getId()).get().getCountry();

            if (flightDto.getDestiny().getName() != null && flightDto.getDestiny().getCity() != null && flightDto.getDestiny().getCountry() != null) {
                if(flightDto.getDestiny().getName().equalsIgnoreCase(name) && flightDto.getDestiny().getCity().equalsIgnoreCase(city) && flightDto.getDestiny().getCountry().equalsIgnoreCase(country)){
                    isValid = true;
                }
                else {
                    throw new AirportBadRequestException("The destiny airport fields do not match the provided name, city, or country");
                }
            }
            else{
                throw new AirportBadRequestException("Destiny airport fields cannot be null");
            }
        }
        else {
            throw new AirportNotFoundException("The destiny airport with ID " + flightDto.getDestiny().getId() + " does not exist");
        }

        return isValid;
    }

    public void delete(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Flight flightToDelete = flightRepository.getById(optId
                        .orElseThrow(() -> new FlightBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new FlightNotFoundException("The flight with ID " + optId.get() + " was not found"));

        flightRepository.delete(flightToDelete.getId());
    }

    public List<Passenger> getAllPassengersById(String id) {
        Optional<Integer> optId = IdConverter.convertToInt(id);
        List<Passenger> passengersList = new ArrayList<>();

        passengerRepository.getAllByFlightId(optId
                        .orElseThrow(() -> new FlightBadRequestException("The ID is an incorrect value")))
                .get()
                .forEach(passenger -> passengersList.add(passenger));

        return passengersList;
    }
    
}
