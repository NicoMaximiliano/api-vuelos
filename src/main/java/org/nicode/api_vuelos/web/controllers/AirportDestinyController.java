package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportDestiny;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IAirportDestinyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportDestinyController {

    private final IAirportDestinyService airportDestinyService;


    @GetMapping("/destinies/all")
    public ResponseEntity<?> getAll(){
        if (airportDestinyService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "No airports available"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(airportDestinyService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/destinies/all/country")
    public ResponseEntity<?> getAllByCountry(@RequestParam("country") String country){
        if (airportDestinyService.getAllByCountry(country).isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "There are no airports available in " + country), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(airportDestinyService.getAllByCountry(country), HttpStatus.OK);
        }
    }

    @GetMapping("/destiny/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(airportDestinyService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/destiny")
    public ResponseEntity<?> getByName(@RequestParam("name") String name){
        return new ResponseEntity<>(airportDestinyService.getByName(name), HttpStatus.OK);
    }

    @PostMapping("/destiny/create")
    public ResponseEntity<?> create(@RequestBody @Valid AirportDestiny airportRequest){
        airportDestinyService.create(airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("destiny/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody AirportDestiny airportRequest){
        airportDestinyService.update(id, airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport with ID " + id +" updated"), HttpStatus.OK);
    }

    @DeleteMapping("/destiny/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        airportDestinyService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airport with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
