package com.github.chat.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chat.payload.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class JsonHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(Envelope.class);


    public static Optional<String> toJson(Object object){
        try {
            log.debug("Before call to method: {}", object);
            return Optional.of(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error("Error: {}",e.getMessage());
        }
        return Optional.empty();
    }

    public static <T> Optional<T> fromJSON(String str, Class<T> clz){
        try {
            log.debug("Before call to method: {}", str);
            return Optional.of(objectMapper.readValue(str, clz));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
}
