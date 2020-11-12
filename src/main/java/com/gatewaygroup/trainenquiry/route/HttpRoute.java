package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.aggregation.TrainsDataAggregation;
import com.gatewaygroup.trainenquiry.model.OpenCageResponse;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.processor.OpenCageRequestProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

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
                .enrich("direct:opencage", new TrainsDataAggregation())
                .marshal().json(JsonLibrary.Jackson)
                .log("${body}")
                .end();


        from("direct:opencage")
                .process(new OpenCageRequestProcessor())
                .log("URI: {{opencage-api-uri}}&q=${in.header.lng}+${in.header.lat}")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .toD("{{opencage-api-uri}}&q=${in.header.lng}%2C${in.header.lat}")
                .log("Opencage Respnse: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, OpenCageResponse.class)
                .end();

    }
}
