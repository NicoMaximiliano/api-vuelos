package org.nicode.api_vuelos.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportDestiny {

    private Integer id;

    @NotBlank(message = "The name filed cannot be blank")
    private String name;

    @NotBlank(message = "The city field cannot be blank")
    private String city;

    @NotBlank(message = "The country field cannot be blank")
    private String country;
}
