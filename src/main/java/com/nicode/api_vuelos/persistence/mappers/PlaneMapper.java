package com.nicode.api_vuelos.persistence.mappers;

import com.nicode.api_vuelos.domain.dtos.Plane;
import com.nicode.api_vuelos.persistence.entities.PlaneEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AirlineMapper.class})
public interface PlaneMapper {

    Plane toPlane(PlaneEntity planeEntity);
    List<Plane> toPlanes(List<PlaneEntity> planeEntities);

    @InheritInverseConfiguration
    PlaneEntity toPlaneEntity(Plane plane);
}
