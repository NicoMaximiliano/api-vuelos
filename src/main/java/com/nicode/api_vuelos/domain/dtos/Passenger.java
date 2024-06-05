package com.nicode.api_vuelos.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    private Integer id;

    @NotBlank(message = "El campo name no puede estar en blanco")
    private String name;

    @NotBlank(message = "El campo lastname no puede estar en blanco")
    private String lastName;

    @NotBlank(message = "El campo passport no puede estar en blanco")
    private String passport;

    @NotBlank(message = "El campo nationality no puede estar en blanco")
    private String nationality;

    @NotNull(message = "El objeto flight no puede ser nulo")
    private Flight flight;
}
