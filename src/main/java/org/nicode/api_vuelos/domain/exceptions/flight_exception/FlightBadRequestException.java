package org.nicode.api_vuelos.domain.exceptions.flight_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FlightBadRequestException extends RuntimeException {
    public FlightBadRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
      return super.getMessage();
    }
}
