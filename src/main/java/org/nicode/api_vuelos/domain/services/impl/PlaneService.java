package org.nicode.api_vuelos.domain.services.impl;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Plane;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneNotFoundException;
import org.nicode.api_vuelos.domain.services.IPlaneService;
import org.nicode.api_vuelos.persistence.repositories.IAirlineRepository;
import org.nicode.api_vuelos.persistence.repositories.IPlaneRepository;
import org.nicode.api_vuelos.util.converters.EndpointConverter;
import org.nicode.api_vuelos.util.converters.IdConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaneService implements IPlaneService {

    private final IPlaneRepository planeRepository;
    private final IAirlineRepository airlineRepository;


    public List<Plane> getAll(){
        return planeRepository.getAll();
    }

    public Plane getById(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        return planeRepository.getById(optId
                        .orElseThrow(() -> new PlaneBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new PlaneNotFoundException("The plane with ID " + optId.get() + " was not found"));
    }

    public Plane getByModel(String model){
        String nameModel = EndpointConverter.transformEndpoint(model);

        return planeRepository.getByModel(nameModel)
                .orElseThrow(() -> new PlaneNotFoundException("The plane " + nameModel + " was not found"));
    }

    public void create(Plane planeDto){
        if (airlineRepository.exist(planeDto.getAirline().getId())){
            String name = airlineRepository.getById(planeDto.getAirline().getId()).get().getName();
            String country = airlineRepository.getById(planeDto.getAirline().getId()).get().getCountry();

            if (planeDto.getAirline().getName() != null && planeDto.getAirline().getCountry() != null){
                if (planeDto.getAirline().getName().equalsIgnoreCase(name) && planeDto.getAirline().getCountry().equalsIgnoreCase(country)) {
                    planeRepository.save(planeDto);
                } else {
                    throw new AirlineBadRequestException("The airline fields not match the provided name or country");
                }
            } else {
                throw new AirlineBadRequestException("Airline fields cannot be null");
            }
        }
        else {
            throw new AirlineNotFoundException("The airline with ID " + planeDto.getAirline().getId() + " does not exist");
        }
    }

    public void update(String id, Plane planeDto){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Plane planeToUpdate = planeRepository.getById(optId
                        .orElseThrow(() -> new PlaneBadRequestException("The ID is an incorrect value")))
                .map(plane -> {
                    if (planeDto.getModel() != null){
                        plane.setModel(planeDto.getModel());
                    }
                    if (planeDto.getCapacity() != null){
                        plane.setCapacity(planeDto.getCapacity());
                    }

                    return plane;
                })
                .orElseThrow(() -> new PlaneNotFoundException("The plane with ID " + optId.get() + " was not found"));

        planeRepository.save(planeToUpdate);
    }

    public void delete(String id){
        Optional<Integer> optId = IdConverter.convertToInt(id);

        Plane planeToDelete = planeRepository.getById(optId
                        .orElseThrow(() -> new PlaneBadRequestException("The ID is an incorrect value")))
                .orElseThrow(() -> new PlaneNotFoundException("The plane with ID " + optId.get() + " was not found"));

        planeRepository.delete(planeToDelete.getId());
    }

}
