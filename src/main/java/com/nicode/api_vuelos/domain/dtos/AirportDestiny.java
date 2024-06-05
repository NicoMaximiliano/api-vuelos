package com.nicode.api_vuelos.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDestiny {

    private Integer id;

    @NotBlank(message = "El campo name no puede estar en blanco")
    private String name;

    @NotBlank(message = "El campo city no puede estar en blanco")
    private String city;

    @NotBlank(message = "El campo country no puede estar en blanco")
    private String country;
}
