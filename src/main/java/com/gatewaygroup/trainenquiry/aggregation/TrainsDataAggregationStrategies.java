package com.gatewaygroup.trainenquiry.aggregation;

import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.model.TrainsDetailsResponse;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainsDataAggregationStrategies implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        Trains[] trains = oldExchange.getMessage().getBody(Trains[].class);
        Map locationData = newExchange.getMessage().getBody(Map.class);
        TrainsDetailsResponse trainsDetailsResponse = new TrainsDetailsResponse();
        trainsDetailsResponse.setTrainDetails(trains[0]);
        trainsDetailsResponse.setAddress(((Map)((List)locationData.get("results")).get(0)).get("formatted").toString());
        if (oldExchange.getPattern().isOutCapable()) {
            oldExchange.getMessage().setBody(trainsDetailsResponse);
        }
        return oldExchange;
    }
}
