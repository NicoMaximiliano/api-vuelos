package org.nicode.api_vuelos.domain.dtos.responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"status", "code", "messages"})
public record MultiErrorResponse(String status, String code, List<String> messages) {
}
