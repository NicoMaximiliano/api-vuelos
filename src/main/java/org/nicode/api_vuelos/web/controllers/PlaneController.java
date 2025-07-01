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
import org.nicode.api_vuelos.domain.dtos.Plane;
import org.nicode.api_vuelos.domain.dtos.responses.SuccessfulResponse;
import org.nicode.api_vuelos.domain.services.IPlaneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/planes")
@Tag(name = "Plane", description = "Controller to manage planes")
@RequiredArgsConstructor
public class PlaneController {

    private final IPlaneService planeService;


    @Operation(
            summary = "Get all planes",
            description = "Show all available planes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of planes successfully obtained",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Plane.class))),
                            @Content(
                                    mediaType = "application/xml",
                                    array = @ArraySchema(schema = @Schema(implementation = Plane.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "List of planes is empty",
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
        if (!planeService.getAll().isEmpty()){
            return new ResponseEntity<>(planeService.getAll(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new SuccessfulResponse("success", "There are no planes"), HttpStatus.OK);
        }
    }




    @Operation(
            summary = "Get plane by id",
            description = "Show an available plane through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Plane successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Plane.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Plane.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect plane id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plane id not found",
                    content = @Content
            )

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Parameter(description = "The plane id being requested", required = true) @PathVariable("id") String id){
        return new ResponseEntity<>(planeService.getById(id), HttpStatus.OK);
    }



    @Operation(
            summary = "Get plane by model",
            description = "Show one available plane through its model name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Plane successfully found",
                    content =  {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Plane.class)),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = Plane.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plane model not found",
                    content = @Content
            )

    })
    @GetMapping(params = "model")
    public ResponseEntity<?> getByModel(@Parameter(description = "The plane model being requested", required = true) @RequestParam("model") String model){
        return new ResponseEntity<>(planeService.getByModel(model), HttpStatus.OK);
    }



    @Operation(
            summary = "Create a new plane",
            description = "Create and save a new plane"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Plane successfully created",
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
    public ResponseEntity<?> create(@Parameter(description = "The plane data to be created", required = true) @RequestBody @Valid Plane planeRequest){
        planeService.create(planeRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "Plane successfully created"), HttpStatus.CREATED);
    }




    @Operation(
            summary = "Update a plane",
            description = "Update and save an existing plane"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Plane successfully updated",
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
                    description = "Incorrect plane id request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plane id not found",
                    content = @Content
            )

    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@Parameter(description = "The plane id being requested", required = true) @PathVariable("id") String id, @Parameter(description = "The plane data to be updated", required = true) @RequestBody Plane planeRequest) {
        planeService.update(id, planeRequest);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The plane with ID " + id +" updated"), HttpStatus.OK);
    }



    @Operation(
            summary = "Remove plane by id",
            description = "Eliminate a plane through its id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Plane successfully eliminated",
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
                    description = "Incorrect plane id request",
                    content = @Content),
            @ApiResponse(
                    responseCode = "404",
                    description = "Plane id not found",
                    content = @Content)

    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "The plane id to delete", required = true) @PathVariable("id") String id){
        planeService.delete(id);
        return new ResponseEntity<>(new SuccessfulResponse("success", "The plane with ID " + id +" eliminated"), HttpStatus.OK);
    }



}
