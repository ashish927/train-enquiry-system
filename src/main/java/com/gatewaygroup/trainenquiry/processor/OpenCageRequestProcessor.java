package com.gatewaygroup.trainenquiry.processor;

import com.gatewaygroup.trainenquiry.model.Coordinate;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.processor.strategy.EuropeSelection;
import com.gatewaygroup.trainenquiry.processor.strategy.StrategyContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OpenCageRequestProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        Trains [] trains = exchange.getMessage().getBody(Trains[].class);
        Coordinate coordinate = new StrategyContext(new EuropeSelection()).executeStrategy(trains[0]);
        exchange.setProperty("lat", coordinate.getLat());
        exchange.setProperty("lng", coordinate.getLng());
    }
}
