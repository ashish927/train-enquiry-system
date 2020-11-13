package com.gatewaygroup.trainenquiry.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;


public class ExceptionHandler implements Processor {
    static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    static Map<Class, HttpStatus> exceptionMap = new HashMap<>();

    static {
        exceptionMap.put(HttpClientErrorException.Unauthorized.class, HttpStatus.UNAUTHORIZED );
        exceptionMap.put(HttpClientErrorException.BadRequest.class, HttpStatus.BAD_REQUEST );
        exceptionMap.put(HttpClientErrorException.UnprocessableEntity.class, HttpStatus.UNPROCESSABLE_ENTITY );
        exceptionMap.put(IncorrectCredentialsException.class, HttpStatus.UNAUTHORIZED);
        exceptionMap.put(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR );
    }



    @Override
    public void process(Exchange exchange) throws Exception {
        Throwable e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
        logger.error(e.getMessage(), e);
        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, exceptionMap.get(e.getClass()).value());
        if(exceptionMap.containsKey(e.getClass())){
            exchange.getMessage().setBody(new ResponseEntity<>(e.getMessage(), exceptionMap.get(e.getClass())));
        }
    }
}
