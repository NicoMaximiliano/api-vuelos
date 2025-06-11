package org.nicode.api_vuelos.util.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nicode.api_vuelos.domain.dtos.Airline;
import org.nicode.api_vuelos.persistence.entities.AirlineEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "country", target = "country")

    })
    Airline toAirline(AirlineEntity airlineEntity);

    List<Airline> toAirlines(List<AirlineEntity> airlineEntities);


    @InheritInverseConfiguration
    AirlineEntity toAirlineEntity(Airline airline);

}
