package com.nicode.api_vuelos.web.controllers;

import com.nicode.api_vuelos.domain.dtos.Flight;
import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.domain.services.impl.FlightService;
import com.nicode.api_vuelos.web.exceptions.flight_exception.FlightNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private Response response;

    @GetMapping("/all")
    public ResponseEntity<?> getFlightAll(){
        return new ResponseEntity<>(flightService.getAllFlightService(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlight(@PathVariable("id") int id){
        return flightService.getFlightService(id)
                .map(flight -> new ResponseEntity<>(flight, HttpStatus.OK))
                .orElseThrow(() -> new FlightNotFoundException("El vuelo con id " + id + " no fue encontrado"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveFlight(@RequestBody @Valid Flight flight){
        return new ResponseEntity<>(flightService.saveFlightService(flight), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlightById(@PathVariable("id") int id) {
        return flightService.deleteFlightService(id)
                .map(flight -> {
                    Map<?,?> body = response.createBody("El vuelo con id "+ id +" ha sido eliminado", HttpStatus.OK.toString(), flight);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new FlightNotFoundException("El vuelo con id " + id + " no fue encontrado"));
    }

}
