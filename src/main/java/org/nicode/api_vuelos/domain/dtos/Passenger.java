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
@Schema(description = "Representation of a passenger")
public class Passenger {

    @Schema(description = "Unique passenger identifier", example = "1")
    private Integer id;

    @Schema(description = "Name of the passenger", example = "John")
    @NotBlank(message = "The name field cannot be blank")
    private String name;

    @Schema(description = "Last name of the passenger", example = "Doe")
    @NotBlank(message = "The lastname field cannot be blank")
    private String lastName;

    @Schema(description = "Passport number of the passenger", example = "A12345678")
    @NotBlank(message = "The passport field cannot be blank")
    private String passport;

    @Schema(description = "Nationality of the passenger", example = "Argentinian")
    @NotBlank(message = "The nationality field cannot be blank")
    private String nationality;

    @NotNull(message = "The flight field cannot be null")
    private Flight flight;
}
