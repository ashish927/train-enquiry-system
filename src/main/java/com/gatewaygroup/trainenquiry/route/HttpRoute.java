package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.aggregation.TrainsDataAggregationStrategies;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.processor.HttpProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class HttpRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:httpRoute")
                .log("Http Route started")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("{{trains-data-uri}}")
                .streamCaching()
                .convertBodyTo(Trains[].class)
                .marshal().json(JsonLibrary.Jackson)
                .log("Response : ${body}")
                .to("direct:opencage");

        from("direct:opencage")
                .process(new HttpProcessor())
                .log("prop: {{opencage-api-uri}}&q=${in.header.lng}+${in.header.lat}")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("{{opencage-api-uri}}&q=${in.header.lng}%2C${in.header.lat}")
                .aggregate(new TrainsDataAggregationStrategies());

    }
}
