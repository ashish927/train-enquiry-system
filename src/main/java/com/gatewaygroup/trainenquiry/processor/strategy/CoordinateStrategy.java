package com.gatewaygroup.trainenquiry.processor.strategy;

import com.gatewaygroup.trainenquiry.model.Coordinate;
import com.gatewaygroup.trainenquiry.model.Trains;

/**
 * Define coordinate selection strategy for lat & lng selection
 */
public interface CoordinateStrategy {
    Coordinate selection(Trains trains);
}
