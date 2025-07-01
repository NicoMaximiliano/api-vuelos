package org.nicode.api_vuelos.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Passenger;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IPassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/passengers")
@Tag(name = "Passenger", description = "Controller to manage passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final IPassengerService passengerService;


    @Operation(
            summary = "Get all passengers",
            description = "Show all available passengers"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of passengers successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No passengers available",
                    content = @Content
            )
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (passengerService.getAll().isEmpty()){
            return new ResponseEntity<>(new SuccessfulResponse("succes", "No passengers available"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(passengerService.getAll(), HttpStatus.OK);
        }
    }




    @Operation(
            summary = "Get passenger by id",
            description = "Show one available passenger through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Passenger successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect passenger id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger id not found",
                    content = @Content
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The passenger id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(passengerService.getById(id), HttpStatus.OK);
    }




    @Operation(
            summary = "Get passenger by full name",
            description = "Show one available passenger through its full name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Passenger successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect passenger first name request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect passenger last name request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger first name not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger last name not found",
                    content = @Content
            )
    })
    @GetMapping(params = {"firstname","lastname"})
    public ResponseEntity<?> getByFullName(@Parameter(description = "The passenger firstname being requested", required = true) @RequestParam("firstname") String firstName, @Parameter(description = "The passenger lastname being requested", required = true) @RequestParam("lastname") String lastName){
        return new ResponseEntity<>(passengerService.getByFullName(firstName, lastName), HttpStatus.OK);
    }




    @Operation(
            summary = "Get passenger by passport number",
            description = "Show one available passenger through its passport number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Passenger successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect passenger passport request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger passport not found",
                    content = @Content
            )

    })
    @GetMapping(params = "passport")
    public ResponseEntity<?> getByPassport(@Parameter(description = "The passenger passport being requested", required = true) @RequestParam("passport") String passport){
        return new ResponseEntity<>(passengerService.getByPassport(passport), HttpStatus.OK);
    }




    @Operation(
            summary = "Create a new passenger",
            description = "Create and save a new passenger"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Passenger successfully created",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            )

    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Parameter(description = "The passenger data to be created", required = true) @RequestBody @Valid Passenger passengerRequest){
        passengerService.create(passengerRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Passenger successfully created"), HttpStatus.CREATED);
    }



    @Operation(
            summary = "Update a passenger",
            description = "Update and save an existing passenger"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Passenger successfully updated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            )

    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The passenger id being requested", required = true) @PathVariable("id") String id, @Parameter(description = "The Passenger data to be updated", required = true) @RequestBody Passenger passengerRequest){
        passengerService.update(id, passengerRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The passenger with ID " + id + " updated"), HttpStatus.OK);
    }




    @Operation(
            summary = "Remove passenger by id",
            description = "Eliminate a passenger through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Passenger successfully eliminated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Passenger.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Passenger.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect passenger id request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger id not found",
                    content = @Content)

    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The passenger id to delete", required = true) @PathVariable("id") String id){
        passengerService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The passenger with ID " + id +" eliminated"), HttpStatus.OK);
    }



}
