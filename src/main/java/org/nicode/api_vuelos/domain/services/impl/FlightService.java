package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Flight;
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

        if(flightDto.getPlane().getId() != null && planeRepository.getById(flightDto.getPlane().getId()).isPresent()) {
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
            if (flightDto.getPlane().getId() == null) {
                throw new PlaneBadRequestException("The plane ID cannot be null");
            }
            throw new PlaneNotFoundException("The plane with ID " + flightDto.getPlane().getId() + " was not found");
        }

        return isValid && validateAirline(flightDto);
    }

    public boolean validateAirline(Flight flightDto) {
        boolean isValid = false;

        if (flightDto.getPlane().getAirline().getId() != null && airlineRepository.getById(flightDto.getPlane().getAirline().getId()).isPresent()) {
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
            if(flightDto.getPlane().getAirline().getId() == null) {
                throw new AirlineBadRequestException("The airline ID cannot be null");
            }
            throw new AirlineNotFoundException("The airline with ID " + flightDto.getPlane().getAirline().getId() + " was not found");
        }

        return isValid;
    }

    public boolean validateAirportOrigin(Flight flightDto) {
        boolean isValid = false;

        if (flightDto.getOrigin().getId() != null && originRepository.getById(flightDto.getOrigin().getId()).isPresent()) {
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
            if(flightDto.getOrigin().getId() == null) {
                throw new AirportBadRequestException("The origin airport ID cannot be null");
            }
            throw new AirportNotFoundException("The origin airport with ID " + flightDto.getOrigin().getId() + " was not found");
        }

        return isValid;
    }

    public boolean validateAirportDestiny(Flight flightDto) {
        boolean isValid = false;

        if (flightDto.getDestiny().getId() != null && destinyRepository.getById(flightDto.getDestiny().getId()).isPresent()) {
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
            if (flightDto.getDestiny().getId() == null) {
                throw new AirportBadRequestException("The destiny airport ID cannot be null");
            }
            throw new AirportNotFoundException("The destiny airport with ID " + flightDto.getDestiny().getId() + " was not found");
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

//    public List<Passenger> getAllPassengersById(String id) {
//        Optional<Integer> optId = IdConverter.convertToInt(id);
//        List<Passenger> passengers;
//
//        Flight flight = flightRepository.getById(optId
//                        .orElseThrow(() -> new FlightBadRequestException("The ID is an incorrect value")))
//                .orElseThrow(() -> new FlightNotFoundException("The flight with ID " + optId.get() + " was not found"));
//
//        passengers = passengerRepository.getAllByFlightId(flight.getId())
//                .get();
//
//        return passengers;
//    }

    public List<Map<String, Object>> getAllPassengersById(String id) {
        Optional<Integer> optId = IdConverter.convertToInt(id);
        List<Map<String, Object>> passengers = new ArrayList<>();

        Flight flight = flightRepository.getById(optId
                        .orElseThrow(() -> new FlightBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new FlightNotFoundException("The flight with ID " + optId.get() + " was not found"));


        passengerRepository.getAllByFlightId(flight.getId())
                .get()
                .forEach(passenger -> {
                    Map<String, Object> passengerMap = new LinkedHashMap<>();
                    passengerMap.put("id", passenger.getId());
                    passengerMap.put("name", passenger.getName());
                    passengerMap.put("lastName", passenger.getLastName());
                    passengerMap.put("passport", passenger.getPassport());
                    passengers.add(passengerMap);
                });

        return passengers;
    }


}
