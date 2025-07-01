package org.nicode.api_vuelos.domain.dtos.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"status", "code", "message"})
public record ErrorResponse(String status, String code, String message) {

}
