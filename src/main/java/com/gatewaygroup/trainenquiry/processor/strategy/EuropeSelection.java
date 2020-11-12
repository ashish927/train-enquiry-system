package com.gatewaygroup.trainenquiry.processor.strategy;

import com.gatewaygroup.trainenquiry.model.Coordinate;
import com.gatewaygroup.trainenquiry.model.Trains;

public class EuropeSelection implements CoordinateStrategy{
    @Override
    public Coordinate selection(Trains trains) {
        Coordinate coordinate = new Coordinate();
        coordinate.setLat(trains.getLocation().getCoordinates().get(0));
        coordinate.setLng(trains.getLocation().getCoordinates().get(1));
        return coordinate;
    }
}
