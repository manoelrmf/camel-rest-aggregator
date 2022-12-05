package br.com.redhat.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final ObjectMapper om = new ObjectMapper();

    public static String objectToJson(Object objeto) {
        String jsonInString = "";
        try {
            jsonInString = om.writeValueAsString(objeto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    public static ObjectMapper getOM(String json) {
        return om;
    }

}