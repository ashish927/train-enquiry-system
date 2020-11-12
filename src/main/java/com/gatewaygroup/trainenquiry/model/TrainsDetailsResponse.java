package com.gatewaygroup.trainenquiry.model;

public class TrainsDetailsResponse {
    private Trains trainDetails;
    private String address;

    public Trains getTrainDetails() {
        return trainDetails;
    }

    public void setTrainDetails(Trains trainDetails) {
        this.trainDetails = trainDetails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
