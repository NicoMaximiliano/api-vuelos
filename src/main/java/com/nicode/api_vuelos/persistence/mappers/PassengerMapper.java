package com.nicode.api_vuelos.persistence.mappers;

import com.nicode.api_vuelos.domain.dtos.Passenger;
import com.nicode.api_vuelos.persistence.entities.PassengerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {FlightMapper.class})
public interface PassengerMapper {

    Passenger toPassenger(PassengerEntity passengerEntity);
    List<Passenger> toPassengers(List<PassengerEntity> passengerEntities);

    @InheritInverseConfiguration
    PassengerEntity toPassengerEntity(Passenger passenger);
}
