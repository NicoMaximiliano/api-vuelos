package org.nicode.api_vuelos.util.converters;

public class EndpointConverter {

    public static String transformEndpoint(String endpoint){
        return endpoint.contains("-") ? endpoint.replace("-"," ") : endpoint;
    }

}
