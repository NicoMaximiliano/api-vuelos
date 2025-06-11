package org.nicode.api_vuelos.persistence.repositories;

import org.nicode.api_vuelos.domain.dtos.Plane;

import java.util.List;
import java.util.Optional;

public interface IPlaneRepository {

    List<Plane> getAll();
    Optional<Plane> getById(int id);
    Optional<Plane> getByModel(String model);
    void save(Plane planeDto);
    void delete(int id);
    boolean exist(int id);
}
