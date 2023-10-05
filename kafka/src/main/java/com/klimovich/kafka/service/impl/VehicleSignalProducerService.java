package com.klimovich.kafka.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.klimovich.kafka.model.VehicleDistance;
import com.klimovich.kafka.model.VehicleSignal;
import com.klimovich.kafka.service.ProducerService;
import com.klimovich.kafka.util.VehicleSignalUtils;

@Service
@Slf4j
public class VehicleSignalProducerService implements ProducerService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.signal.name}")
    private String signalTopic;

    @Value("${kafka.topic.distance.name}")
    private String distanceTopic;

    @Override
    public void sendSignalToKafkaTopic(VehicleSignal vehicleSignal) {
        log.info("Signal sent to Topic");
        VehicleSignalUtils.saveIfAbsent(vehicleSignal);
        this.kafkaTemplate.send( signalTopic, vehicleSignal.getId(), vehicleSignal);
    }

    @Override
    public void sendDistanceToKafkaTopic(VehicleDistance vehicleDistance) {
        log.info("Distance sent to Topic");
        VehicleSignalUtils.saveIfAbsent(vehicleDistance);
        this.kafkaTemplate.send( distanceTopic, vehicleDistance.getId(), vehicleDistance);
    }
}
