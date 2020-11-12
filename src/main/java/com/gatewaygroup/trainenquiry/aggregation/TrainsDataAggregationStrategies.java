package com.gatewaygroup.trainenquiry.aggregation;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class TrainsDataAggregationStrategies implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        return newExchange;
    }
}
