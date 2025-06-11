package org.nicode.api_vuelos.util.mappers;

import org.nicode.api_vuelos.domain.dtos.Flight;
import org.nicode.api_vuelos.persistence.entities.FlightEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring",uses = {PlaneMapper.class, AirportMapper.class})
public interface FlightMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "start", target = "start", dateFormat = "yyyy-MM-dd HH-mm-ss"),
            @Mapping(source = "finish", target = "finish", dateFormat = "yyyy-MM-dd HH-mm-ss"),
            @Mapping(source = "plane", target = "plane"),
            @Mapping(source = "origin", target = "origin"),
            @Mapping(source = "destiny", target = "destiny")
    })
    Flight toFlight(FlightEntity flightEntity);
    List<Flight> toFlights(List<FlightEntity> flightEntities);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "plane.airline",target = "airline"),
            @Mapping(target = "passengers", ignore = true)
    })
    FlightEntity toFlightEntity(Flight flight);

}
