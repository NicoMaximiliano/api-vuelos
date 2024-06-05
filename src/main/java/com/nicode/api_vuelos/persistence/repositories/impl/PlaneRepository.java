package com.nicode.api_vuelos.persistence.repositories.impl;

import com.nicode.api_vuelos.domain.dtos.Plane;
import com.nicode.api_vuelos.persistence.repositories.IPlaneRepository;
import com.nicode.api_vuelos.persistence.entities.PlaneEntity;
import com.nicode.api_vuelos.persistence.mappers.PlaneMapper;
import com.nicode.api_vuelos.persistence.repositories.crud.PlaneCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaneRepository implements IPlaneRepository {

    @Autowired
    private PlaneCrudRepository planeCrudRepository;

    @Autowired
    private PlaneMapper planeMapper;

    @Override
    public List<Plane> getAll() {
        List<PlaneEntity> planeEntities = planeCrudRepository.findAll();

        return planeMapper.toPlanes(planeEntities);
    }

    @Override
    public Optional<Plane> getById(int id) {
        return planeCrudRepository.findById(id)
                .map(planeEntity -> planeMapper.toPlane(planeEntity));
    }

    @Override
    public Optional<Plane> getByModel(String model) {
        return planeCrudRepository.findByModel(model)
                .map(planeEntity -> planeMapper.toPlane(planeEntity));
    }

    @Override
    public Plane save(Plane plane) {
        PlaneEntity planeEntity = planeMapper.toPlaneEntity(plane);

        return planeMapper.toPlane(planeCrudRepository.save(planeEntity));
    }

    @Override
    public void delete(int id) {
        planeCrudRepository.deleteById(id);
    }
}
