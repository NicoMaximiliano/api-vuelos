package org.nicode.api_vuelos.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a destination airport")
public class AirportDestiny {

    @Schema(description = "Unique destination airport identifier", example = "1")
    private Integer id;

    @Schema(description = "Name of the destination airport", example = "Aeropuerto Internacional de Rio de Janeiro")
    @NotBlank(message = "The name filed cannot be blank")
    private String name;

    @Schema(description = "City where the destination airport is located", example = "Rio de Janeiro")
    @NotBlank(message = "The city field cannot be blank")
    private String city;

    @Schema(description = "Country where the destination airport is located", example = "Brasil")
    @NotBlank(message = "The country field cannot be blank")
    private String country;
}
