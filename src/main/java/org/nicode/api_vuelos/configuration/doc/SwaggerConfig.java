package org.nicode.api_vuelos.configuration.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                title = "API Vuelos",
                description = "This API manages flights at an airport",
                version = "1.0.0",
                contact = @Contact(
                        name = "Nicolas Orellano",
                        email = "nico.orellano@gmail.com"
                ),
                license = @License(
                        name = "Standard Software Use License for NiCode"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://localhost:8080/vuelos/api/v1"
                )
        }
)
public class SwaggerConfig { }
