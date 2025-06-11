package org.nicode.api_vuelos.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of an airline")
public class Airline {

    @Schema(description = "Unique airline identifier", example = "1")
    private Integer id;

    @Schema(description = "Name of the airline")
    @NotBlank(message = "The name field cannot be blank")
    private String name;

    @Schema(description = "Country to which the airline belongs", example = "Argentina")
    @NotBlank(message = "The country field cannot be blank")
    private String country;


}
