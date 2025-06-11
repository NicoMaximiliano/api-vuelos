package org.nicode.api_vuelos.domain.services;

import org.nicode.api_vuelos.domain.dtos.Plane;

import java.util.List;

public interface IPlaneService {

    List<Plane> getAll();
    Plane getById(String id);
    Plane getByModel(String model);
    void create(Plane planeDto);
    void update(String id, Plane planeDto);
    void delete(String id);
}
