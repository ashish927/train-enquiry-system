package com.gatewaygroup.trainenquiry;

import com.gatewaygroup.trainenquiry.converter.ResponseConverter;
import com.gatewaygroup.trainenquiry.route.HttpRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.PropertiesComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainEnquiryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainEnquiryApplication.class, args);
    }

    @Bean("HttpRouteBean")
    ProducerTemplate producerTemplate() throws Exception {
        CamelContext context = new DefaultCamelContext();
        PropertiesComponent pc = context.getPropertiesComponent();
        pc.setLocation("classpath:application.yml");
        context.addRoutes(new HttpRoute());
        context.getTypeConverterRegistry().addTypeConverters(new ResponseConverter());
        context.setTracing(true);
        context.setDebugging(true);
        context.setStreamCaching(true);
        context.start();
        return context.createProducerTemplate();
    }
}
