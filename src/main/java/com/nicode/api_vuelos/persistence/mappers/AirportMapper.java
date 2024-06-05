package com.nicode.api_vuelos.persistence.mappers;

import com.nicode.api_vuelos.domain.dtos.AirportDestiny;
import com.nicode.api_vuelos.domain.dtos.AirportOrigin;
import com.nicode.api_vuelos.persistence.entities.AirportDestinyEntity;
import com.nicode.api_vuelos.persistence.entities.AirportOriginEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper{

    AirportDestiny toAirportDestiny(AirportDestinyEntity airportEntity);
    List<AirportDestiny> toAirportsDestinies(List<AirportDestinyEntity> airportsEntities);
    AirportOrigin toAirportOrigin(AirportOriginEntity airportEntity);
    List<AirportOrigin> toAirportsOrigins(List<AirportOriginEntity> airportsEntities);

    @InheritInverseConfiguration
    AirportDestinyEntity toAirportDestinyEntity(AirportDestiny airportDestiny);
    AirportOriginEntity toAirportOriginEntity(AirportOrigin airportOrigin);
}
