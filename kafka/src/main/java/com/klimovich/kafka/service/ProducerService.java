package com.klimovich.kafka.service;

import com.klimovich.kafka.model.VehicleDistance;
import com.klimovich.kafka.model.VehicleSignal;

public interface ProducerService {

    void sendSignalToKafkaTopic(VehicleSignal vehicleSignal);

    void sendDistanceToKafkaTopic(VehicleDistance vehicleDistance);
}
