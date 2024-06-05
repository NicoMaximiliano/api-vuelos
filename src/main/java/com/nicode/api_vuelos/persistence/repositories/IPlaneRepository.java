package com.nicode.api_vuelos.persistence.repositories;

import com.nicode.api_vuelos.domain.dtos.Plane;

import java.util.List;
import java.util.Optional;

public interface IPlaneRepository {

    List<Plane> getAll();
    Optional<Plane> getById(int id);
    Optional<Plane> getByModel(String model);
    Plane save(Plane plane);
    void delete(int id);
}
