package org.nicode.api_vuelos.persistence.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Plane;
import org.nicode.api_vuelos.persistence.repositories.IPlaneRepository;
import org.nicode.api_vuelos.persistence.entities.PlaneEntity;
import org.nicode.api_vuelos.util.mappers.PlaneMapper;
import org.nicode.api_vuelos.persistence.repositories.crud.PlaneCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaneRepository implements IPlaneRepository {

    private final PlaneCrudRepository planeCrudRepository;
    private final PlaneMapper planeMapper;

    @Override
    public List<Plane> getAll() {
        List<PlaneEntity> planeEntities = planeCrudRepository.findAll();
        return planeMapper.toPlanes(planeEntities);
    }

    @Override
    public Optional<Plane> getById(int id) {
        return planeCrudRepository.findById(id)
                .map(planeMapper::toPlane);
    }

    @Override
    public Optional<Plane> getByModel(String model) {
        return planeCrudRepository.findByModel(model)
                .map(planeMapper::toPlane);
    }

    @Override
    public void save(Plane planeDto) {
        PlaneEntity planeEntity = planeMapper.toPlaneEntity(planeDto);
        planeCrudRepository.save(planeEntity);
        //return planeMapper.toPlane(planeCrudRepository.save(planeEntity));
    }

    @Override
    public void delete(int id) {
        planeCrudRepository.deleteById(id);
    }

    @Override
    public boolean exist(int id) {
        return planeCrudRepository.existsById(id);
    }
}
