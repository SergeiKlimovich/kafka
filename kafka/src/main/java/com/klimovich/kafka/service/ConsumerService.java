package com.klimovich.kafka.service;

import com.klimovich.kafka.model.VehicleSignal;

public interface ConsumerService {

    void consumer1(VehicleSignal vehicleSignal);

    void consumer2(VehicleSignal vehicleSignal);

    void consumer3(VehicleSignal vehicleSignal);
}
