package com.nicode.api_vuelos.web.controllers;

import com.nicode.api_vuelos.domain.dtos.Airline;
import com.nicode.api_vuelos.domain.services.IAirlineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airlines")
public class AirlineController {
    @Autowired
    private IAirlineService airlineService;

    @GetMapping("/all")
    public ResponseEntity<?> getAirlineAll(){
        return new ResponseEntity<>(airlineService.getAllAirlineService(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable("id") int id){
        return new ResponseEntity<>(airlineService.getAirlineServiceById(id),HttpStatus.OK);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> getAirlineById(@PathVariable("id") int id){
        return airlineService.getAirlineServiceById(id)
                .map(airline -> new ResponseEntity<>(airline, HttpStatus.OK))
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea con id " + id + " no fue encontrada"));
    }*/

    @GetMapping(params = "name")
    public ResponseEntity<?> getAirlineByName(@RequestParam("name") String name){
        return new ResponseEntity<>(airlineService.getAirlineServiceByName(name), HttpStatus.OK);
    }

    /*
    @GetMapping(params = "name")
    public ResponseEntity<?> getAirlineByName(@RequestParam("name") String name){
        return airlineService.getAirlineServiceByName(name)
                .map(airline -> new ResponseEntity<>(airline, HttpStatus.OK))
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea " + name + " no fue encontrada"));
    }*/

    @PostMapping("/save")
    public ResponseEntity<?> saveAirline(@RequestBody @Valid Airline airline) {
        return new ResponseEntity<>(airlineService.saveAirlineService(airline), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAirlineById(@PathVariable("id") int id) {
        return new ResponseEntity<>(airlineService.deleteAirlineService(id), HttpStatus.OK);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAirlineById(@PathVariable("id") int id) {
        return airlineService.deleteAirlineService(id)
                .map(airline -> {
                    Map<?,?> body = response.createBody("La aerolinea con id "+ id +" ha sido eliminada", HttpStatus.OK.toString(), airline);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new AirlineNotFoundException("La aerolinea con id " + id + " no fue encontrada"));
    }*/

}
