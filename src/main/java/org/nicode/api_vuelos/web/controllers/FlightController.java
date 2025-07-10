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
import org.nicode.api_vuelos.domain.dtos.Flight;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IFlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/flights")
@Tag(name = "Flight", description = "Controller to manage flights")
@RequiredArgsConstructor
public class FlightController {

    private final IFlightService flightService;


    @Operation(
            summary = "Get all flights",
            description = "Show all available flights"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of flights successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Flight.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = Flight.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of flights is empty",
                    content =  @Content
            )
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (!flightService.getAll().isEmpty()){
            return new ResponseEntity<>(flightService.getAll(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    @Operation(
            summary = "Get flight by id",
            description = "Show one available flight by its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Flight successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Flight.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Flight.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect flight id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Flight not found",
                    content = @Content
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The flight id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(flightService.getById(id), HttpStatus.OK);
    }




    @Operation(
            summary = "Create a new flight",
            description = "Create and save a new flight"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Flight successfully created",
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
    @PostMapping("/create")
    public ResponseEntity<?> create(@Parameter(description = "The flight data to be created", required = true) @RequestBody @Valid Flight flightRequest) {
        flightService.create(flightRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Flight successfully created"), HttpStatus.CREATED);
    }




    @Operation(
            summary = "Update a flight",
            description = "Update and save an existing flight"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Flight successfully updated",
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
                    description = "Incorrect flight id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Flight id not found",
                    content = @Content
            )

    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The flight id being requested", required = true) @PathVariable("id") String id, @Parameter(description = "The flight data to be updated", required = true) @RequestBody Flight flightRequest) {
        flightService.update(id, flightRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The flight with ID " + id + " successfully updated"), HttpStatus.OK);
    }




    @Operation(
            summary = "Remove flight by id",
            description = "Delete a flight through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Flight successfully eliminated",
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
                    description = "Incorrect flight id request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Flight id not found",
                    content = @Content)

    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The flight id being requested", required = true) @PathVariable("id") String id) {
        flightService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The flight with ID " + id +" eliminated"), HttpStatus.OK);
    }




    @Operation(
            summary = "Get all passengers on a flight",
            description = "Show all passengers on a specific flight"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of passengers successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Passenger.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = Passenger.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of passengers is empty",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect flight id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Flight id not found",
                    content = @Content
            )

    })
    @GetMapping("/{id}/all-passengers")
    public ResponseEntity<?> getAllPassengers(@Parameter(description = "The flight id being requested", required = true) @PathVariable("id") String id){
        List<Map<String, Object>> passengers = flightService.getAllPassengersById(id);

        if (!passengers.isEmpty()){
            return new ResponseEntity<>(flightService.getAllPassengersById(id), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
