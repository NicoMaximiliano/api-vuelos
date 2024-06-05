package com.nicode.api_vuelos.persistence.mappers;

import com.nicode.api_vuelos.domain.dtos.Airline;
import com.nicode.api_vuelos.persistence.entities.AirlineEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    Airline toAirline(AirlineEntity airlineEntity);
    List<Airline> toAirlines(List<AirlineEntity> airlineEntities);

    @InheritInverseConfiguration
    AirlineEntity toAirlineEntity(Airline airline);
}
