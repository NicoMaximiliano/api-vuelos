package com.nicode.api_vuelos.web.controllers;

import com.nicode.api_vuelos.domain.dtos.Plane;
import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.domain.services.impl.PlaneService;
import com.nicode.api_vuelos.web.exceptions.plane_exception.PlaneNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @Autowired
    private Response response;

    @GetMapping("/all")
    public ResponseEntity<?> getPlaneAll(){
        return new ResponseEntity<>(planeService.getAllPlaneService(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaneById(@PathVariable("id") int id){
        return planeService.getPlaneServiceById(id)
                .map(plane -> new ResponseEntity<>(plane, HttpStatus.OK))
                .orElseThrow(() -> new PlaneNotFoundException("El avion con id " + id + " no fue encontrado"));
    }

    @GetMapping(params = "model")
    public ResponseEntity<?> getPlaneByModel(@RequestParam("model") String model){
        return planeService.getPlaneServiceByModel(model)
                .map(plane -> new ResponseEntity<>(plane, HttpStatus.OK))
                .orElseThrow(() -> new PlaneNotFoundException("El avion modelo " + model + " no fue encontrado"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePlane(@RequestBody @Valid Plane plane){
        return new ResponseEntity<>(planeService.savePlaneService(plane), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlaneById(@PathVariable("id") int id){
        return planeService.deletePlaneServiceById(id)
                .map(plane -> {
                    Map<?,?> body = response.createBody("El avion con id "+ id +" ha sido eliminado", HttpStatus.OK.toString(), plane);
                    return new ResponseEntity<>(body, HttpStatus.OK);
                })
                .orElseThrow(() -> new PlaneNotFoundException("El avion con id " + id + " no fue encontrado"));
    }

}
