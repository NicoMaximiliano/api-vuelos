package com.nicode.api_vuelos.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {

    private Integer id;

    @NotBlank(message = "El campo model no puede estar en blanco")
    private String model;

    @NotNull(message = "El campo capacity no puede ser nulo")
    private Integer capacity;

    @NotNull(message = "El objeto Airline no puede ser nulo")
    private Airline airline;

}
