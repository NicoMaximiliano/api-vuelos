package com.nicode.api_vuelos.domain.services.impl;

import com.nicode.api_vuelos.domain.dtos.Plane;
import com.nicode.api_vuelos.persistence.repositories.IPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private IPlaneRepository planeRepository;

    public List<Plane> getAllPlaneService(){
        return planeRepository.getAll();
    }

    public Optional<Plane> getPlaneServiceById(int id){
        return planeRepository.getById(id);
    }

    public Optional<Plane> getPlaneServiceByModel(String model){
        //String model = plane.getModel();
        String m = model.replace("-"," ");
        return planeRepository.getByModel(m);
    }

    public Plane savePlaneService(Plane plane){
        return planeRepository.save(plane);
    }

    public Optional<Plane> deletePlaneServiceById(int id){
        return getPlaneServiceById(id)
                .map(plane -> {
                    planeRepository.delete(id);
                    return plane;
                });
    }

    /*
    public boolean deletePlaneServiceById(int id){
        return getPlaneServiceById(id)
                .map(plane -> {
                    planeRepository.delete(id);
                    return true;
                })
                .orElse(false);
    }*/


}
