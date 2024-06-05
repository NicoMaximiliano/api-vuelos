package com.nicode.api_vuelos.web.controllers;

import com.nicode.api_vuelos.domain.dtos.AirportDestiny;
import com.nicode.api_vuelos.domain.dtos.AirportOrigin;
import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.domain.services.impl.AirportService;
import com.nicode.api_vuelos.web.exceptions.airport_exception.AirportNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private Response response;

    @GetMapping("/destinies/all")
    public ResponseEntity<?> getAllDestinies(){
        return new ResponseEntity<>(airportService.getAllDestinyService(), HttpStatus.OK);
    }

    @GetMapping("/destiny/{id}")
    public ResponseEntity<?> getDestinyById(@PathVariable("id") int id){
        return airportService.getDestinyServiceById(id)
                .map(airportDestiny -> new ResponseEntity<>(airportDestiny, HttpStatus.OK))
                .orElseThrow(()-> new AirportNotFoundException("El aeropuerto de destino con id " + id + " no fue encontrado"));
    }

    @GetMapping("/destiny")
    public ResponseEntity<?> getDestinyByName(@RequestParam("name") String name){
        return airportService.getDestinyServiceByName(name)
                .map(airportD -> new ResponseEntity<>(airportD, HttpStatus.OK))
                .orElseThrow(()-> new AirportNotFoundException("El aeropuerto de destino " + name + " no fue encontrado"));
    }

    @GetMapping("/destinies/all/country")
    public ResponseEntity<?> getAllDestiniesByCountry(@RequestParam("country") String country){
        return new ResponseEntity<>(airportService.getAllDestinyServiceByCountry(country), HttpStatus.OK);
    }

    @PostMapping("/destiny/save")
    public ResponseEntity<?> saveDestiny(@RequestBody @Valid AirportDestiny destiny){
        return new ResponseEntity<>(airportService.saveAirportDestinyService(destiny), HttpStatus.CREATED);
    }

    @DeleteMapping("/destiny/delete/{id}")
    public ResponseEntity<?> deleteAirportDestinyById(@PathVariable("id") int id) {
        return airportService.deleteAirportDestinyService(id)
                .map(airport -> {
                    Map<?,?> body = response.createBody("El aeropuerto de destino con id "+ id +" ha sido eliminado", HttpStatus.OK.toString(), airport);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new AirportNotFoundException("El aeropuerto de destino con id " + id + " no fue encontrado"));
    }




    @GetMapping("/origins/all")
    public ResponseEntity<?> getAllOrigins(){
        return new ResponseEntity<>(airportService.getAllOriginService(), HttpStatus.OK);
    }

    @GetMapping("/origin/{id}")
    public ResponseEntity<?> getOriginById(@PathVariable("id") int id){
        return airportService.getOriginServiceById(id)
                .map(airportOrigin -> new ResponseEntity<>(airportOrigin, HttpStatus.OK))
                .orElseThrow(()-> new AirportNotFoundException("El aeropuerto de origen con id " + id + " no fue encontrado"));
    }

    @GetMapping("/origin")
    public ResponseEntity<?> getOriginByName(@RequestParam("name") String name){
        return airportService.getOriginServiceByName(name)
                .map(airportO -> new ResponseEntity<>(airportO, HttpStatus.OK))
                .orElseThrow(()-> new AirportNotFoundException("El aeropuerto de origen " + name + " no fue encontrado"));
    }

    @GetMapping("/origins/all/country")
    public ResponseEntity<?> getAllOriginsByCountry(@RequestParam("country") String country){
        return new ResponseEntity<>(airportService.getAllOriginServiceByCountry(country), HttpStatus.OK);
    }

    @PostMapping("/origin/save")
    public ResponseEntity<?> saveOrigin(@RequestBody @Valid AirportOrigin origin){
        return new ResponseEntity<>(airportService.saveAirportOriginService(origin), HttpStatus.CREATED);
    }

    @DeleteMapping("/origin/delete/{id}")
    public ResponseEntity<?> deleteAirportOriginById(@PathVariable("id") int id) {
        return airportService.deleteAirportOriginService(id)
                .map(airport -> {
                    Map<?,?> body = response.createBody("El aeropuerto de origen con id "+ id +" ha sido eliminado", HttpStatus.OK.toString(), airport);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new AirportNotFoundException("El aeropuerto de origen con id " + id + " no fue encontrado"));
    }
}
