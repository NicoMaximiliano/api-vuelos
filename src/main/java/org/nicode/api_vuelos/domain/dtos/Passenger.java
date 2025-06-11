package org.nicode.api_vuelos.domain.dtos;

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

    @NotBlank(message = "The name field cannot be blank")
    private String name;

    @NotBlank(message = "The lastname field cannot be blank")
    private String lastName;

    @NotBlank(message = "The passport field cannot be blank")
    private String passport;

    @NotBlank(message = "The nationality field cannot be blank")
    private String nationality;

    @NotNull(message = "The flight field cannot be null")
    private Flight flight;
}
