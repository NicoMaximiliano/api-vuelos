package org.nicode.api_vuelos.domain.exceptions.airline_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AirlineBadRequestException extends RuntimeException{

    public AirlineBadRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();

    }

}
