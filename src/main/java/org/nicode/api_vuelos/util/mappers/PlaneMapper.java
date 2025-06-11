package org.nicode.api_vuelos.util.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nicode.api_vuelos.domain.dtos.Plane;
import org.nicode.api_vuelos.persistence.entities.PlaneEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AirlineMapper.class})
public interface PlaneMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "model", target = "model"),
            @Mapping(source = "capacity", target = "capacity"),
            @Mapping(source = "airline", target = "airline")
    })
    Plane toPlane(PlaneEntity planeEntity);
    List<Plane> toPlanes(List<PlaneEntity> planeEntities);


    @InheritInverseConfiguration
    @Mapping(target = "flights", ignore = true)
    PlaneEntity toPlaneEntity(Plane plane);

}
