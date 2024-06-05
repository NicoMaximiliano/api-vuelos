package com.nicode.api_vuelos.web.exceptions.config;

import com.nicode.api_vuelos.domain.response.Response;
import com.nicode.api_vuelos.web.exceptions.airline_exception.AirlineBadRequestException;
import com.nicode.api_vuelos.web.exceptions.airline_exception.AirlineNotFoundException;
import com.nicode.api_vuelos.web.exceptions.airport_exception.AirportNotFoundException;
import com.nicode.api_vuelos.web.exceptions.flight_exception.FlightNotFoundException;
import com.nicode.api_vuelos.web.exceptions.passenger_exception.PassengerBadRequestException;
import com.nicode.api_vuelos.web.exceptions.passenger_exception.PassengerNotFoundException;
import com.nicode.api_vuelos.web.exceptions.plane_exception.PlaneNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AdviceExceptionHandler{

    @Autowired
    private Response response;

    @ExceptionHandler({
            AirlineNotFoundException.class,
            PassengerNotFoundException.class,
            PlaneNotFoundException.class,
            FlightNotFoundException.class,
            AirportNotFoundException.class
    })
    protected ResponseEntity<?> handleNotFoundAirline(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(response.createBody(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AirlineBadRequestException.class, PassengerBadRequestException.class})
    protected ResponseEntity<?> handleBadRequestAirline(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(response.createBody(ex.getMessage(), HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInvalidArguments(MethodArgumentNotValidException exception) {
        List<String> messages = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            messages.add(error.getDefaultMessage());
        });

        return new ResponseEntity<>(response.createBody(messages, HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }
}
