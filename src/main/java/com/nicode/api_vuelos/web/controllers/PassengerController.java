package com.nicode.api_vuelos.web.controllers;

import com.nicode.api_vuelos.domain.dtos.Passenger;
import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.domain.services.impl.PassengerService;
import com.nicode.api_vuelos.web.exceptions.passenger_exception.PassengerNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private Response response;

    @GetMapping("/all")
    public ResponseEntity<?> getPassengerAll(){
        return new ResponseEntity<>(passengerService.getAllPassengerService(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPassengerById(@PathVariable("id") int id){
        return passengerService.getPassengerServiceById(id)
                .map(passenger -> new ResponseEntity<>(passenger, HttpStatus.OK))
                .orElseThrow(() -> new PassengerNotFoundException("El pasajero con id " + id + " no fue encontrado"));
    }

    @GetMapping(params = {"name","lastname"})
    public ResponseEntity<?> getPassengerByFullName(@RequestParam("name") String name, @RequestParam("lastname") String lastName){
        return passengerService.getPassengerServiceByFullName(name, lastName)
                .map(passenger -> new ResponseEntity<>(passenger, HttpStatus.OK))
                .orElseThrow(() -> new PassengerNotFoundException("El pasajero con nombre " + name + " " + lastName + " no fue encontrado"));
    }

    /*
    @GetMapping("/fullname")
    public ResponseEntity<?> getPassengerByFullName(@RequestBody Passenger passenger){
        return passengerService.getPassengerServiceByFullName(passenger)
                .map(passenger1 -> new ResponseEntity<>(passenger1, HttpStatus.OK))
                .orElseThrow(() -> new PassengerNotFoundException("El pasajero con nombre " + passenger.getName() + " " + passenger.getLastName() + " no fue encontrado"));
    }*/

    @GetMapping(params = "passport")
    public ResponseEntity<?> getPassengerByPassport(@RequestParam("passport") String passport){
        return passengerService.getPassengerServiceByPassport(passport)
                .map(passenger -> new ResponseEntity<>(passenger, HttpStatus.OK))
                .orElseThrow(() -> new PassengerNotFoundException("El pasajero con pasaporte " + passport + " no fue encontrado"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid Passenger passenger){
        return new ResponseEntity<>(passengerService.savePassengerService(passenger), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePassengerById(@PathVariable("id") int id){
        return passengerService.deletePassengerService(id)
                .map(passenger -> {
                    Map<?,?> body = response.createBody("El pasajero con id "+ id +" ha sido eliminado", HttpStatus.OK.toString(), passenger);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new PassengerNotFoundException("El pasajero con id " + id + " no fue encontrado"));
    }

}
