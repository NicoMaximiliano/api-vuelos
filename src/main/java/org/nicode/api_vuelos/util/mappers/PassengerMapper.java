package org.nicode.api_vuelos.util.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.persistence.entities.PassengerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring",uses = {FlightMapper.class})
public interface PassengerMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "lastName", target = "lastName"),
            @Mapping(source = "passport", target = "passport"),
            @Mapping(source = "nationality", target = "nationality"),
            @Mapping(source = "flight", target = "flight")
    })
    Passenger toPassenger(PassengerEntity passengerEntity);
    List<Passenger> toPassengers(List<PassengerEntity> passengerEntities);


    @InheritInverseConfiguration
    PassengerEntity toPassengerEntity(Passenger passenger);

}
