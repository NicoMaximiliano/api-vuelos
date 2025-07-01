package org.nicode.api_vuelos.web.controllers;


import io.swagger.v3.oas.annotations.media.ArraySchema;
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
                                    array = @ArraySchema(schema = @Schema(implementation = Airline.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = Airline.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of airlines is empty",
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
    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        if (!airlineService.getAll().isEmpty()) {
            return new ResponseEntity<>(airlineService.getAll(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new SuccessfulResponse("success", "No airlines available"), HttpStatus.NO_CONTENT);
        }
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Get airline by id",
            description = "Show an available airline through its id"
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
            summary = "Get airline by name",
            description = "Show an available airline through its name"
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
                    responseCode = "404",
                    description = "Airline name not found",
                    content = @Content
            )

    })
    @GetMapping(params = "name")
    public ResponseEntity<?> getByName(@Parameter(description = "The airline name being requested", required = true) @RequestParam("name") String name){
        return new ResponseEntity<>(airlineService.getByName(name), HttpStatus.OK);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Create a new airline",
            description = "Create and save a new airline"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Airline successfully created",
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
    public ResponseEntity<?> create(@Parameter(description = "The Airline data to be created", required = true) @RequestBody @Valid Airline airlineRequest) {
        airlineService.create(airlineRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Airline successfully created"), HttpStatus.CREATED);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Update a airline",
            description = "Update and save an existing airline"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Airline successfully updated",
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
                    description = "Incorrect airline id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airline id not found",
                    content = @Content
            )

    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The airline id to be updated", required = true) @PathVariable("id") String id, @Parameter(description = "The airline data to be updated", required = true) @RequestBody Airline airlineRequest) {
        airlineService.update(id, airlineRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The airline with ID " + id +" updated"), HttpStatus.OK);
    }


    /* --------------------------------------------------------- */
    @Operation(
            summary = "Remove airlines by id",
            description = "Delete an airline through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Airline successfully eliminated",
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
                    description = "Incorrect airline id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Airline id not found",
                    content = @Content
            )

    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The airline id to delete", required = true) @PathVariable("id") String id) {
        airlineService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The airline with ID " + id +" eliminated"), HttpStatus.OK);
    }


}
