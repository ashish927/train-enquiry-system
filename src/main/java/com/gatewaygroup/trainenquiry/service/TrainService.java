package com.gatewaygroup.trainenquiry.service;

import com.gatewaygroup.trainenquiry.converter.ResponseConverter;
import com.gatewaygroup.trainenquiry.model.Trains;
import com.gatewaygroup.trainenquiry.route.HttpRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrainService {

    ProducerTemplate producerTemplate;

    public TrainService() throws Exception {
        CamelContext context = new DefaultCamelContext();
        PropertiesComponent pc = context.getPropertiesComponent();
        pc.setLocation("classpath:application.yml");
        HttpRoute httpRoute = new HttpRoute();
        context.addRoutes(httpRoute);
        context.getTypeConverterRegistry().addTypeConverters(new ResponseConverter());
        context.setDebugging(true);
        context.setStreamCaching(true);
        context.start();
        this.producerTemplate = context.createProducerTemplate();

    }
    public ResponseEntity getTrains(){
        String result = producerTemplate.requestBodyAndHeaders("direct:httpRoute", null,null, String.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
