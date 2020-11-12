package com.gatewaygroup.trainenquiry.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.model.TrainsDetailsResponse;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;
import org.apache.camel.converter.stream.CachedOutputStream;
import org.apache.camel.converter.stream.InputStreamCache;

import java.io.IOException;

public class ResponseConverter implements TypeConverters {

    @Converter
    public Trains[] inputStreamCacheToTrainsArray(InputStreamCache source) {
        try {
            return new ObjectMapper().readValue(source.readAllBytes(), Trains[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Converter
    public TrainsDetailsResponse inputStreamCacheToTrainsDetailsResponse(InputStreamCache source) {
        try {
            return new ObjectMapper().readValue(source.readAllBytes(), TrainsDetailsResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Converter
    public Trains[] cachedOutputStreamToCityMap(CachedOutputStream source) {
        try {
            return new ObjectMapper().readValue(source.getInputStream().readAllBytes(), Trains[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
