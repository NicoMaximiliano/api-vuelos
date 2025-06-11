package org.nicode.api_vuelos.util.converters;

import java.util.Optional;

public class IdConverter {

    public static Optional<Integer> convertToInt(String id){
        return isNumericId(id) ? Optional.of(Integer.parseInt(id)) : Optional.empty();
    }

    public static boolean isNumericId(String id){
        boolean isNumeric = true;
        for(int i=0; i<id.length(); i++){
            if(!Character.isDigit(id.charAt(i))) isNumeric = false;
        }
        return isNumeric;
    }

}
