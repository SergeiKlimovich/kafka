package com.klimovich.kafka.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.klimovich.kafka.model.VehicleDistance;
import com.klimovich.kafka.model.VehicleSignal;
import com.klimovich.kafka.service.ConsumerService;
import com.klimovich.kafka.service.ProducerService;
import com.klimovich.kafka.util.VehicleSignalUtils;

@Slf4j
@Service
public class VehicleSignalConsumerService implements ConsumerService {

    @Autowired
    private ProducerService producerService;

    @Override
    @KafkaListener(topics = "${kafka.topic.signal.name}", groupId = "kafka_task_demo")
    public void consumer1(VehicleSignal vehicleSignal) {
        VehicleSignal latestSavedSignal = VehicleSignalUtils.getLatestSavedSignal(vehicleSignal);
        Double distance = getDistance(latestSavedSignal, vehicleSignal);
        VehicleSignalUtils.saveLatestSignal(vehicleSignal);

        producerService.sendDistanceToKafkaTopic(VehicleDistance.builder().id(vehicleSignal.getId()).distance(distance).build());
    }

    @KafkaListener(topics = "${kafka.topic.signal.name}", groupId = "kafka_task_demo")
    public void consumer2(VehicleSignal vehicleSignal) {
        VehicleSignal latestSavedSignal = VehicleSignalUtils.getLatestSavedSignal(vehicleSignal);
        Double distance = getDistance(vehicleSignal, latestSavedSignal);
        VehicleSignalUtils.saveLatestSignal(vehicleSignal);

        producerService.sendDistanceToKafkaTopic(VehicleDistance.builder().id(vehicleSignal.getId()).distance(distance).build());
    }

    @KafkaListener(topics = "${kafka.topic.signal.name}", groupId = "kafka_task_demo")
    public void consumer3(VehicleSignal vehicleSignal) {
        VehicleSignal latestSavedSignal = VehicleSignalUtils.getLatestSavedSignal(vehicleSignal);
        Double distance = getDistance(vehicleSignal, latestSavedSignal);
        VehicleSignalUtils.saveLatestSignal(vehicleSignal);

        producerService.sendDistanceToKafkaTopic(VehicleDistance.builder().id(vehicleSignal.getId()).distance(distance).build());
    }

    @KafkaListener(topics = "${kafka.topic.distance.name}")
    public void distanceLogConsumer(VehicleDistance vehicleDistance) {
        VehicleSignalUtils.addToDistanceTraveled(vehicleDistance);
        log.info("\n ID: " + vehicleDistance.getId() +
                "\n Distance: " + vehicleDistance.getDistance() + " Km.");
    }

    private Double getDistance(VehicleSignal vehicleSignal, VehicleSignal latestSavedSignal) {
        return VehicleSignalUtils.distanceInKM(
                vehicleSignal.getLatitude(),
                latestSavedSignal.getLatitude(),
                vehicleSignal.getLongitude(),
                latestSavedSignal.getLongitude());
    }

}
