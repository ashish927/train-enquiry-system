package com.gatewaygroup.trainenquiry.service;

import com.gatewaygroup.trainenquiry.model.TrainsDetailsResponse;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainService {

    @Autowired
    ProducerTemplate producerTemplate;

    public ResponseEntity<TrainsDetailsResponse> getTrainsDetails() {
        TrainsDetailsResponse result = producerTemplate.requestBodyAndHeaders("direct:httpRoute", null, null, TrainsDetailsResponse.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
