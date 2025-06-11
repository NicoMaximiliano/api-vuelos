package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IPassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final IPassengerService passengerService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (passengerService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("succes", "No passengers available"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(passengerService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(passengerService.getById(id), HttpStatus.OK);
    }

    @GetMapping(params = {"firstname","lastname"})
    public ResponseEntity<?> getByFullName(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName){
        return new ResponseEntity<>(passengerService.getByFullName(firstName, lastName), HttpStatus.OK);
    }

    @GetMapping(params = "passport")
    public ResponseEntity<?> getByPassport(@RequestParam("passport") String passport){
        return new ResponseEntity<>(passengerService.getByPassport(passport), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid Passenger passengerRequest){
        passengerService.create(passengerRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Passenger successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Passenger passengerRequest){
        passengerService.update(id, passengerRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Passenger with ID " + id + " updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        passengerService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Passenger with ID " + id +" eliminated"), HttpStatus.OK);
    }

}
