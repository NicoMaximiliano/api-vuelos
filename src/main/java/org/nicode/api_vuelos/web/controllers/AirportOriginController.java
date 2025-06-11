package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportOrigin;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IAirportOriginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportOriginController {

    private final IAirportOriginService airportOriginService;


    @GetMapping("/origins/all")
    public ResponseEntity<?> getAll(){
        if (airportOriginService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "No airports available"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(airportOriginService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/origins/all/country")
    public ResponseEntity<?> getAllByCountry(@RequestParam("country") String country){
        if (airportOriginService.getAllByCountry(country).isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "There are no airports available in " + country), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(airportOriginService.getAllByCountry(country), HttpStatus.OK);
        }
    }

    @GetMapping("/origin/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(airportOriginService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/origin")
    public ResponseEntity<?> getByName(@RequestParam("name") String name){
        return new ResponseEntity<>(airportOriginService.getByName(name), HttpStatus.OK);
    }

    @PostMapping("/origin/create")
    public ResponseEntity<?> create(@RequestBody @Valid AirportOrigin airportRequest){
        airportOriginService.create(airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("origin/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody AirportOrigin airportRequest){
        airportOriginService.update(id, airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport with ID " + id +" updated"), HttpStatus.OK);
    }

    @DeleteMapping("/origin/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        airportOriginService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
