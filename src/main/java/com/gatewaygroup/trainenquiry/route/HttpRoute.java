package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.model.Trains;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class HttpRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:httpRoute")
                .log("Http Route started")
                .setHeader("Content-Type", constant("application/json"))
                .setHeader("Accept", constant("application/json"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .removeHeader(Exchange.HTTP_PATH)
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("${in.headers.uri}")
                .streamCaching()
                .convertBodyTo(Trains[].class)
                .marshal().json(JsonLibrary.Jackson)
                .log("Response : ${body}");
    }
}
