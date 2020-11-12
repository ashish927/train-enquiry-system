package com.gatewaygroup.trainenquiry.model;

public class TrainsDetailsResponse {
    private Trains trainDetails;
    private String currentLocation;

    public Trains getTrainDetails() {
        return trainDetails;
    }

    public void setTrainDetails(Trains trainDetails) {
        this.trainDetails = trainDetails;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
