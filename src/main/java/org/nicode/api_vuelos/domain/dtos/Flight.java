package org.nicode.api_vuelos.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a flight")
public class Flight {

    @Schema(description = "Unique flight identifier", example = "1")
    private Integer id;

    @Schema(description = "Flight price", example = "500.00")
    @NotNull(message = "The price field cannot be null")
    private Float price;

    @Schema(description = "Flight start date and time", example = "2023-10-01T10:00:00")
    @NotNull(message = "The start field cannot be null")
    private LocalDateTime start;

    @Schema(description = "Flight finish date and time", example = "2023-10-01T12:00:00")
    @NotNull(message = "The finish field cannot be null")
    private LocalDateTime finish;

    @NotNull(message = "The plane field cannot be null")
    private Plane plane;

    @NotNull(message = "The origin field cannot be null")
    private AirportOrigin origin;

    @NotNull(message = "The destiny field cannot be null")
    private AirportDestiny destiny;

}
