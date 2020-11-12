package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.exception.ExceptionHandler;
import com.gatewaygroup.trainenquiry.service.TrainService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class WebRoute extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
                .contextPath("/gatewaygroup/api/")
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Location Service REST API")
                .apiProperty("api.version", "1.0.0")
                .apiProperty("cors", "true")
                .apiContextRouteId("doc-api")
                .port("8080")
                .bindingMode(RestBindingMode.json);

        rest("/trains").description("Trains information")
                .consumes("application/json")
                .produces("application/json")

                .get().description("List of trains")
                .route().routeId("get-trains-details")
                .bean(TrainService.class, "getTrainsDetails")
                .errorHandler(defaultErrorHandler())
                .endRest();

        onException(Throwable.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "Route failed due to :"+ simple("${exception.message}"))
                .bean(ExceptionHandler.class, "prepareResponseBody(${exchange}, ${exception})")
                .marshal().json(JsonLibrary.Jackson)
                .end();

    }

}
