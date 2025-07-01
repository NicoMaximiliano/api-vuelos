package org.nicode.api_vuelos.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.AirportDestiny;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IAirportDestinyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airports")
@Tag(name = "Destination Airport", description = "Controller to manage destination airports")
@RequiredArgsConstructor
public class AirportDestinyController {

    private final IAirportDestinyService airportDestinyService;


    @Operation(
            summary = "Get all destination airports",
            description = "Show all available destination airports"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of destination airports successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportDestiny.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportDestiny.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of destination airports is empty",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SuccessfulResponse.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = SuccessfulResponse.class))
                    }
            )
    })
    @GetMapping("/destinies/all")
    public ResponseEntity<?> getAll(){
        if (!airportDestinyService.getAll().isEmpty()){
            return new ResponseEntity<>(airportDestinyService.getAll(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new SuccessfulResponse("success", "There are no destination airports"), HttpStatus.OK);
        }
    }




    @Operation(
            summary = "Get destination airports by country",
            description = "Show all available destination airports by country"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of destination airports successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportDestiny.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportDestiny.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The destination airports not found",
                    content = @Content
            )

    })
    @GetMapping("/destinies/all/country")
    public ResponseEntity<?> getAllByCountry(@Parameter(description = "The country being requested", required = true) @RequestParam("country") String country){
        return new ResponseEntity<>(airportDestinyService.getAllByCountry(country), HttpStatus.OK);
    }




    @Operation(
            summary = "Get destination airport by id",
            description = "Show an available destination airport through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination airport successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AirportDestiny.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = AirportDestiny.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect destination airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination airport id not found",
                    content = @Content
            )

    })
    @GetMapping("/destiny/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The destination airport id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(airportDestinyService.getById(id), HttpStatus.OK);
    }





    @Operation(
            summary = "Get destination airport by name",
            description = "Show an available destination airport through its name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination airport successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AirportDestiny.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = AirportDestiny.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect destination airport name request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination airport name not found",
                    content = @Content
            )

    })
    @GetMapping("/destiny")
    public ResponseEntity<?> getByName(@Parameter(description = "The destination airport name being requested", required = true) @RequestParam("name") String name){
        return new ResponseEntity<>(airportDestinyService.getByName(name), HttpStatus.OK);
    }




    @Operation(
            summary = "Create a new destination airport",
            description = "Create and save a new destination airport"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Destination airport successfully created",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SuccessfulResponse.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = SuccessfulResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "The fields cannot be blank",
                    content = @Content
            )

    })
    @PostMapping("/destiny/create")
    public ResponseEntity<?> create(@Parameter(description = "The destination airport data to be created", required = true) @RequestBody @Valid AirportDestiny airportRequest){
        airportDestinyService.create(airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Destination airport successfully created"), HttpStatus.CREATED);
    }




    @Operation(
            summary = "Update a destination airport",
            description = "Update and save an existing destination airport"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Destination airport successfully updated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SuccessfulResponse.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = SuccessfulResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect destination airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination airport id not found",
                    content = @Content
            )

    })
    @PutMapping("destiny/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The destination airport id being requested", required = true) @PathVariable("id") String id, @Parameter(description = "The destination airport data will be updated", required = true) @RequestBody AirportDestiny airportRequest){
        airportDestinyService.update(id, airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Destination airport with ID " + id +" updated"), HttpStatus.OK);
    }




    @Operation(
            summary = "Remove destination airport by id",
            description = "Eliminate a destination airport through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination airport successfully eliminated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SuccessfulResponse.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = SuccessfulResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect destination airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination airport id not found",
                    content = @Content
            )

    })
    @DeleteMapping("/destiny/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The destination airport id to delete", required = true) @PathVariable("id") String id) {
        airportDestinyService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Destination airport with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
