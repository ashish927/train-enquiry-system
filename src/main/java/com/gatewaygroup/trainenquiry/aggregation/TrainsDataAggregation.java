package com.gatewaygroup.trainenquiry.aggregation;

import com.gatewaygroup.trainenquiry.model.OpenCageResponse;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.model.TrainsDetailsResponse;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.Map;

public class TrainsDataAggregation implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Trains[] trains = oldExchange.getMessage().getBody(Trains[].class);
        OpenCageResponse locationData = newExchange.getMessage().getBody(OpenCageResponse.class);
        TrainsDetailsResponse trainsDetailsResponse = new TrainsDetailsResponse();
        trainsDetailsResponse.setTrainDetails(trains[0]);
        trainsDetailsResponse.setCurrentLocation(((Map)(locationData.getResults()).get(0)).get("formatted").toString());
        if (oldExchange.getPattern().isOutCapable()) {
            oldExchange.getMessage().setBody(trainsDetailsResponse);
        }
        return oldExchange;
    }
}
