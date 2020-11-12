package com.gatewaygroup.trainenquiry.processor.strategy;

import com.gatewaygroup.trainenquiry.model.Coordinate;
import com.gatewaygroup.trainenquiry.model.Trains;

public class StrategyContext {

    private CoordinateStrategy strategy;

    public StrategyContext(CoordinateStrategy strategy){
        this.strategy = strategy;
    }

    public Coordinate executeStrategy(Trains trains){
        return strategy.selection(trains);
    }
}
