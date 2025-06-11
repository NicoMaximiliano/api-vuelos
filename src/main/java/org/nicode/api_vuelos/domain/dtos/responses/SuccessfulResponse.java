package org.nicode.api_vuelos.domain.dtos.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status","message"})
public record SuccessfulResponse(String status, String message) {
}
