package com.nicode.api_vuelos.persistence.mappers;

import com.nicode.api_vuelos.domain.dtos.Flight;
import com.nicode.api_vuelos.persistence.entities.FlightEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring",uses = {PlaneMapper.class, AirportMapper.class})
public interface FlightMapper {

    @Mappings({
            @Mapping(source = "start", target = "start", dateFormat = "yyyy-MM-dd HH-mm-ss"),
            @Mapping(source = "finish", target = "finish", dateFormat = "yyyy-MM-dd HH-mm-ss")
    })
    Flight toFlight(FlightEntity flightEntity);
    List<Flight> toFlights(List<FlightEntity> flightEntities);

    @InheritInverseConfiguration
    @Mapping(source = "plane.airline",target = "airline")
    FlightEntity toFlightEntity(Flight flight);
}
