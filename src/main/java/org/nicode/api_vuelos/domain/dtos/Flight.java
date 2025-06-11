package org.nicode.api_vuelos.domain.dtos;

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

    @NotNull(message = "The price field cannot be null")
    private Float price;

    @NotNull(message = "The start field cannot be null")
    private LocalDateTime start;

    @NotNull(message = "The finish field cannot be null")
    private LocalDateTime finish;

    @NotNull(message = "The plane field cannot be null")
    private Plane plane;

    @NotNull(message = "The origin field cannot be null")
    private AirportOrigin origin;

    @NotNull(message = "The destiny field cannot be null")
    private AirportDestiny destiny;

}
