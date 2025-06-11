package org.nicode.api_vuelos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.nicode.api_vuelos.domain.dtos.Airline;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IAirlineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airlines")
@Tag(name = "Airline", description = "Controller to manage airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final IAirlineService airlineService;


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Get all airlines",
            description = "Show all available airlines"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of airlines successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No airlines available",
                    content = @Content
            )
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (airlineService.getAll().isEmpty()) {
            return new ResponseEntity<>(new SuccessfulResponse("success", "No airlines available"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(airlineService.getAll(), HttpStatus.OK);
        }
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Get airlines by id",
            description = "Show one available airlines"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Airline successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect airline id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airline id not found",
                    content = @Content
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The airline id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(airlineService.getById(id), HttpStatus.OK);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Get airlines by name",
            description = "Show one available airlines"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Airline successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect airline name request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airline name not found",
                    content = @Content)

    })
    @GetMapping(params = "name")
    public ResponseEntity<?> getByName(@Parameter(description = "The name of the airline being requested", required = true) @RequestParam("name") String name){
        return new ResponseEntity<>(airlineService.getByName(name), HttpStatus.OK);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Create a new airline",
            description = "Save an airline"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Airline successfully created",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            )

    })
    @PostMapping("/create")
    public ResponseEntity<?> create(@Parameter(description = "Airline data to be created", required = true) @RequestBody @Valid Airline airlineRequest) {
        airlineService.create(airlineRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airline successfully created"), HttpStatus.CREATED);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Update a airline",
            description = "Save an airline"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Airline successfully updated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            )

    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Parameter(description = "Airline data to be updated", required = true) @RequestBody Airline airlineRequest) {
        airlineService.update(id, airlineRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airline with ID " + id +" updated"), HttpStatus.OK);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Remove airlines by id",
            description = "Eliminate an airline"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Airline successfully eliminated",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Airline.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Airline.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect airline id request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airline id not found",
                    content = @Content)

    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The airline id to delete", required = true) @PathVariable("id") String id) {
        airlineService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airline with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
