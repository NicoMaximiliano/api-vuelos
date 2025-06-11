package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Plane;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IPlaneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/planes")
@RequiredArgsConstructor
public class PlaneController {

    private final IPlaneService planeService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (planeService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("success", "No planes available"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(planeService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(planeService.getById(id), HttpStatus.OK);
    }

    @GetMapping(params = "model")
    public ResponseEntity<?> getByModel(@RequestParam("model") String model){
        return new ResponseEntity<>(planeService.getByModel(model), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody @Valid Plane plane){
        planeService.create(plane);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Plane successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Plane planeRequest) {
        planeService.update(id, planeRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Plane with ID " + id +" updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        planeService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Plane with ID " + id +" eliminated"), HttpStatus.OK);
    }

}
