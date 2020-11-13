package com.gatewaygroup.trainenquiry.route;

import com.gatewaygroup.trainenquiry.exception.ExceptionHandler;
import com.gatewaygroup.trainenquiry.service.TrainService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.shiro.security.ShiroSecurityPolicy;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.gatewaygroup.trainenquiry.util.ShiroUtil.iniResourcePath;
import static com.gatewaygroup.trainenquiry.util.ShiroUtil.passPhrase;

@Component
public class WebRoute extends RouteBuilder {

    @Override
    public void configure() {
        final ShiroSecurityPolicy securityPolicy =
                new ShiroSecurityPolicy(iniResourcePath, passPhrase);
        securityPolicy.setBase64(true);
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
                .outType(ResponseEntity.class)
                .route().routeId("get-trains-details")
                .policy(securityPolicy)
                .bean(TrainService.class)
                .endRest();

        onException(Throwable.class)
                .maximumRedeliveries(1)
                .handled(true)
                .log(LoggingLevel.ERROR, "Route failed due to :"+ simple("${exception.message}"))
                .process(new ExceptionHandler())
                .stop();

    }

}
