package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.aggregation.TrainsDataAggregationStrategies;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.processor.OpenCageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HttpRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("direct:httpRoute")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("{{trains-data-uri}}")
                .streamCaching()
                .convertBodyTo(Trains[].class)
                .marshal().json(JsonLibrary.Jackson)
                .log("Trains Response : ${body}")
                .enrich("direct:opencage", new TrainsDataAggregationStrategies())
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}");
        ;

        from("direct:opencage")
                .process(new OpenCageProcessor())
                .log("URI: {{opencage-api-uri}}&q=${in.header.lng}+${in.header.lat}")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("{{opencage-api-uri}}&q=${in.header.lng}%2C${in.header.lat}")
                .log("Opencage Respnse: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, Map.class);

    }
}
