package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Flight;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IFlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightService flightService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (flightService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "No flights available"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(flightService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Flight flightRequest) {
        flightService.create(flightRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Flight successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Flight flightRequest) {
        flightService.update(id, flightRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Flight with ID " + id + " successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        flightService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Flight with ID " + id +" eliminated"), HttpStatus.OK);
    }

    @GetMapping("/{id}/all-passengers")
    public ResponseEntity<?> getAllPassengers(@PathVariable("id") String id){
        if (flightService.getAllPassengersById(id).isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "No passengers available for flight with ID " + id), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(flightService.getAllPassengersById(id), HttpStatus.OK);
        }
    }


}
