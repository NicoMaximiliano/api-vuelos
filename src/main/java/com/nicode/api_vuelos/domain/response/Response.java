package com.nicode.api_vuelos.domain.response;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class Response {
    private final LocalDateTime timestamp = LocalDateTime.now();

    public Map<?,?> createBody(String message, String status){
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("Timestamp", timestamp);
        body.put("Message", message);
        body.put("Status", status);

        return body;
    }

    public Map<?,?> createBody(List<String> messages, String status){
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("Timestamp", timestamp);
        body.put("Message", messages);
        body.put("Status", status);

        return body;
    }

    public Map<?,?> createBody(String message, String status, Object object){
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("Timestamp", timestamp);
        body.put("Message", message);
        body.put("Status", status);
        body.put(object.getClass().getName(), object);

        return body;
    }

}
