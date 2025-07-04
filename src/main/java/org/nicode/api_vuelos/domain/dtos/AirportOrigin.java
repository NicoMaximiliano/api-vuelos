package org.nicode.api_vuelos.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a origin airport")
public class AirportOrigin {

    @Schema(description = "Unique origin airport identifier", example = "1")
    private Integer id;

    @Schema(description = "Name of the origin airport", example = "Aeropuerto Internacional de Ezeiza")
    @NotBlank(message = "The name field cannot be blank")
    private String name;

    @Schema(description = "City where the origin airport is located", example = "Buenos Aires")
    @NotBlank(message = "The city field cannot be blank")
    private String city;

    @Schema(description = "Country where the origin airport is located", example = "Argentina")
    @NotBlank(message = "The country field cannot be blank")
    private String country;
}
