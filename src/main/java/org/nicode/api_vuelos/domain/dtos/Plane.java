package org.nicode.api_vuelos.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {

    @Schema(description = "Unique plane identifier", example = "1")
    private Integer id;

    @Schema(description = "Model of the plane", example = "Boeing 737")
    @NotBlank(message = "The model field cannot be blank")
    private String model;

    @Schema(description = "Capacity of the plane in terms of number of passengers", example = "180")
    @NotNull(message = "The capacity field cannot be null")
    private Integer capacity;

    //@Schema(description = "Airline that operates the plane", example = "Aerolíneas Argentinas")
    @NotNull(message = "The Airline field cannot be null")
    private Airline airline;

}
