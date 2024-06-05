package com.nicode.api_vuelos.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    private Integer id;

    @NotNull(message = "El campo price no puede ser nulo")
    private Float price;

    @NotNull(message = "El campo start no puede ser nulo")
    private LocalDateTime start;

    @NotNull(message = "El campo finish no puede ser nulo")
    private LocalDateTime finish;

    @NotNull(message = "El campo plane no puede ser nulo")
    private Plane plane;

    @NotNull(message = "El campo origin no puede ser nulo")
    private AirportOrigin origin;

    @NotNull(message = "El campo destiny no puede ser nulo")
    private AirportDestiny destiny;
}
