package org.nicode.api_vuelos.domain.dtos;

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

    @NotBlank(message = "The model field cannot be blank")
    private String model;

    @NotNull(message = "The capacity field cannot be null")
    private Integer capacity;

    @NotNull(message = "The Airline field cannot be null")
    private Airline airline;

}
