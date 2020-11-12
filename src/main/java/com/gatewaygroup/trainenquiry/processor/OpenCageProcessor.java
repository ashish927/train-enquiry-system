package com.gatewaygroup.trainenquiry.processor;

import com.gatewaygroup.trainenquiry.model.Trains;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OpenCageProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Trains [] trains = exchange.getMessage().getBody(Trains[].class);
        exchange.setProperty("lat", trains[0].getLocation().getCoordinates().get(0));
        exchange.setProperty("lng", trains[0].getLocation().getCoordinates().get(1));
    }
}
