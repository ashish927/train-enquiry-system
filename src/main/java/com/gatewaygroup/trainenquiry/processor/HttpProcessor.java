package com.gatewaygroup.trainenquiry.processor;

import com.gatewaygroup.trainenquiry.model.Trains;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class HttpProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Trains [] trains = exchange.getIn().getBody(Trains[].class);
        exchange.setProperty("lat", trains[0].getLocation().getCoordinates().get(0));
        exchange.setProperty("lng", trains[0].getLocation().getCoordinates().get(1));
    }
}
