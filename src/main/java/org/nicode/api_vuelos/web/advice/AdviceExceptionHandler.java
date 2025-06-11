package org.nicode.api_vuelos.web.advice;

import org.nicode.api_vuelos.domain.dtos.responses.ErrorResponse;
import org.nicode.api_vuelos.domain.dtos.responses.MultiErrorResponse;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airline_exception.AirlineNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.airport_exception.AirportNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.flight_exception.FlightNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.passenger_exception.PassengerBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.passenger_exception.PassengerNotFoundException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneBadRequestException;
import org.nicode.api_vuelos.domain.exceptions.plane_exception.PlaneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceExceptionHandler{

    @ExceptionHandler({
            AirlineNotFoundException.class,
            PassengerNotFoundException.class,
            PlaneNotFoundException.class,
            FlightNotFoundException.class,
            AirportNotFoundException.class
    })
    protected ResponseEntity<?> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.NOT_FOUND.toString(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            AirlineBadRequestException.class,
            PassengerBadRequestException.class,
            PlaneBadRequestException.class,
            AirportBadRequestException.class,
            FlightBadRequestException.class
    })
    protected ResponseEntity<?> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException exception) {
//        List<String> messages = new ArrayList<>();
//        Map<String, Object> body = new LinkedHashMap<>();
//
//        exception.getBindingResult().getFieldErrors().forEach(error -> {
//            messages.add(error.getDefaultMessage());
//        });
//
//        body.put("Message: ", messages);
//        body.put("Error: ", HttpStatus.BAD_REQUEST.toString());
//
//        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException exception) {
        List<String> messages = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(error.getDefaultMessage());
        });

        if (messages.size() == 1){
            return new ResponseEntity<>(new ErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), messages.get(0)), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new MultiErrorResponse("error", HttpStatus.BAD_REQUEST.toString(), messages), HttpStatus.BAD_REQUEST);
        }

    }
}
