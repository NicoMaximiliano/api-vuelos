package org.nicode.api_vuelos.util.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nicode.api_vuelos.domain.dtos.AirportDestiny;
import org.nicode.api_vuelos.domain.dtos.AirportOrigin;
import org.nicode.api_vuelos.persistence.entities.AirportDestinyEntity;
import org.nicode.api_vuelos.persistence.entities.AirportOriginEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "country", target = "country")
    })
    AirportDestiny toAirportDestiny(AirportDestinyEntity airportEntity);
    List<AirportDestiny> toAirportsDestinies(List<AirportDestinyEntity> airportsEntities);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "city", target = "city"),
            @Mapping(source = "country", target = "country")
    })
    AirportOrigin toAirportOrigin(AirportOriginEntity airportEntity);
    List<AirportOrigin> toAirportsOrigins(List<AirportOriginEntity> airportsEntities);


    @InheritInverseConfiguration
    @Mapping(target = "flights", ignore = true)
    AirportDestinyEntity toAirportDestinyEntity(AirportDestiny airportDestiny);


    @InheritInverseConfiguration
    @Mapping(target = "flights", ignore = true)
    AirportOriginEntity toAirportOriginEntity(AirportOrigin airportOrigin);

}
