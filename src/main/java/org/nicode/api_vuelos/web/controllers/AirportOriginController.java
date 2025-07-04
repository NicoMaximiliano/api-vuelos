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
import org.nicode.api_vuelos.domain.dtos.AirportOrigin;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IAirportOriginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/airports")
@Tag(name = "Origin Airport", description = "Controller to manage origin airports")
@RequiredArgsConstructor
public class AirportOriginController {

    private final IAirportOriginService airportOriginService;


    @Operation(
            summary = "Get all origin airports",
            description = "Show all available origin airports"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of origin airports successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportOrigin.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportOrigin.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of origin airports is empty",
                    content = @Content
            )
    })
    @GetMapping("/origins/all")
    public ResponseEntity<?> getAll(){
        if (!airportOriginService.getAll().isEmpty()){
            return new ResponseEntity<>(airportOriginService.getAll(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    @Operation(
            summary = "Get origin airports by country",
            description = "Show all available origin airports by country"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of origin airports successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportOrigin.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = AirportOrigin.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "The origin airports not found",
                    content = @Content
            )

    })
    @GetMapping("/origins/all/country")
    public ResponseEntity<?> getAllByCountry(@Parameter(description = "The country being requested", required = true) @RequestParam("country") String country){
        return new ResponseEntity<>(airportOriginService.getAllByCountry(country), HttpStatus.OK);
    }




    @Operation(
            summary = "Get origin airport by id",
            description = "Show an available origin airport through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Origin airport successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AirportOrigin.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = AirportOrigin.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect origin airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Origin airport id not found",
                    content = @Content
            )

    })
    @GetMapping("/origin/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The origin airport id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(airportOriginService.getById(id), HttpStatus.OK);
    }




    @Operation(
            summary = "Get origin airport by name",
            description = "Show an available origin airport through its name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Origin airport successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AirportOrigin.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = AirportOrigin.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect origin airport name request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Origin airport name not found",
                    content = @Content
            )

    })
    @GetMapping("/origin")
    public ResponseEntity<?> getByName(@Parameter(description = "The origin airport name being requested", required = true) @RequestParam("name") String name){
        return new ResponseEntity<>(airportOriginService.getByName(name), HttpStatus.OK);
    }




    @Operation(
            summary = "Create a new origin airport",
            description = "Create and save a new origin airport"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Origin airport successfully created",
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
    @PostMapping("/origin/create")
    public ResponseEntity<?> create(@Parameter(description = "The origin airport data to be created", required = true) @RequestBody @Valid AirportOrigin airportRequest){
        airportOriginService.create(airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Origin airport successfully created"), HttpStatus.CREATED);
    }




    @Operation(
            summary = "Update a origin airport",
            description = "Update and save an existing origin airport"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Origin airport successfully updated",
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
                    description = "Incorrect origin airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Origin airport id not found",
                    content = @Content
            )

    })
    @PutMapping("origin/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The origin airport id being requested", required = true) @PathVariable("id") String id, @Parameter(description = "The origin airport data will be updated", required = true) @RequestBody AirportOrigin airportRequest){
        airportOriginService.update(id, airportRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Origin airport with ID " + id +" updated"), HttpStatus.OK);
    }




    @Operation(
            summary = "Remove origin airport by id",
            description = "Eliminate an origin airport through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Origin airport successfully eliminated",
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
                    description = "Incorrect origin airport id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Origin airport id not found",
                    content = @Content
            )

    })
    @DeleteMapping("/origin/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The origin airport id to delete", required = true) @PathVariable("id") String id) {
        airportOriginService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Origin airport with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
