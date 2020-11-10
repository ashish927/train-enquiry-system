package com.gatewaygroup.trainenquiry.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewaygroup.trainenquiry.model.Trains;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.apache.camel.converter.stream.InputStreamCache;

import java.io.IOException;

public class ResponseConverter implements TypeConverters {

    @Converter
    public Trains[] inputStreamCacheToCityMap(InputStreamCache source) {
        try {
            return new ObjectMapper().readValue(source.readAllBytes(), Trains[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
