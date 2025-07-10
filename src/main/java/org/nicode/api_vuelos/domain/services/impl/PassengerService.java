package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.passenger_exception.PassengerBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.passenger_exception.PassengerNotFoundException;
import org.nicode.api_vuelos.domain.services.IPassengerService;
import org.nicode.api_vuelos.persistence.repositories.IFlightRepository;
import org.nicode.api_vuelos.persistence.repositories.IPassengerRepository;
import org.nicode.api_vuelos.util.converters.IdConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService implements IPassengerService {

    private final IPassengerRepository passengerRepository;
    private final IFlightRepository flightRepository;


//    public List<Passenger> getAll(){
//        return passengerRepository.getAll();
//    }

    public List<Map<String, Object>> getAll(){
        List<Map<String, Object>> passengers = new ArrayList<>();
        passengerRepository.getAll()
                .forEach(passenger -> {
                    Map<String, Object> passengerMap = Map.of(
                            "id", passenger.getId(),
                            "name", passenger.getName(),
                            "lastName", passenger.getLastName(),
                            "passport", passenger.getPassport(),
                            "nationality", passenger.getNationality()
                    );
                    passengers.add(passengerMap);
                });

        return passengers;
    }

    public Passenger getById(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        return passengerRepository.getById(optId
                        .orElseThrow(() -> new PassengerBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new PassengerNotFoundException("The passenger with ID " + optId.get() + " was not found"));
    }

    public Passenger getByFullName(String firstName, String lastName){
        return passengerRepository.getByFullName(firstName, lastName)
                .orElseThrow(() -> new PassengerNotFoundException("The passenger with name " + firstName.toUpperCase() + " " + lastName.toUpperCase() + " was not found"));
    }

    public Passenger getByPassport(String passport){
        return passengerRepository.getByPassport(passport)
                .orElseThrow(() -> new PassengerNotFoundException("The passenger with passport " + passport.toUpperCase() + " was not found"));
    }

    public void create(Passenger passengerDto){
        if (validateFlight(passengerDto)){
            passengerRepository.save(passengerDto);
        }
        else {
            throw new PassengerBadRequestException("The flight associated with the passenger is invalid or does not match the provided details.");
        }
    }

    public boolean validateFlight(Passenger passengerDto) {
        boolean isValid = false;

        if (passengerDto.getFlight().getId() != null && flightRepository.getById(passengerDto.getFlight().getId()).isPresent()) {
            Float price = flightRepository.getById(passengerDto.getFlight().getId()).get().getPrice();
            LocalDateTime departure = flightRepository.getById(passengerDto.getFlight().getId()).get().getStart();
            LocalDateTime arrival = flightRepository.getById(passengerDto.getFlight().getId()).get().getFinish();

            if(passengerDto.getFlight().getPrice() != null && passengerDto.getFlight().getStart() != null && passengerDto.getFlight().getFinish() != null) {
                if (passengerDto.getFlight().getPrice().equals(price) && passengerDto.getFlight().getStart().equals(departure) && passengerDto.getFlight().getFinish().equals(arrival)) {
                    isValid = true;
                } else {
                    throw new FlightBadRequestException("The flight fields do not match the provided price, start or finish time.");
                }
            } else {
                throw new FlightBadRequestException("The flight fields cannot be null.");
            }
        }
        else {
            if (passengerDto.getFlight().getId() == null) {
                throw new FlightBadRequestException("The flight ID cannot be null.");
            }
            throw new FlightNotFoundException("The flight ID not found");
        }

        return isValid;
    }

    @Override
    public void update(String id, Passenger passengerDto) {
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Passenger passengerToUpdate = passengerRepository.getById(optId
                        .orElseThrow(() -> new PassengerBadRequestException("The ID is an incorrect value")))
                .map(passenger -> {
                    if (passengerDto.getName() != null) {
                        passenger.setName(passengerDto.getName());
                    }
                    if (passengerDto.getLastName() != null) {
                        passenger.setLastName(passengerDto.getLastName());
                    }
                    if (passengerDto.getPassport() != null) {
                        passenger.setPassport(passengerDto.getPassport());
                    }
                    if (passengerDto.getNationality() != null){
                        passenger.setNationality(passengerDto.getNationality());
                    }

                    return passenger;
                })
                .orElseThrow(() -> new PassengerNotFoundException("The passenger with ID " + optId.get() + " was not found"));

        passengerRepository.save(passengerToUpdate);
    }

    public void delete(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Passenger passengerToDelete = passengerRepository.getById(optId
                        .orElseThrow(() -> new PassengerBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new PassengerNotFoundException("The passenger with ID " + optId.get() + " was not found"));

        passengerRepository.delete(passengerToDelete.getId());
    }

}
