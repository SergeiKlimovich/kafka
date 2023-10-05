package com.klimovich.kafka.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klimovich.kafka.model.VehicleSignal;

@RequestMapping(value = "/vehicle")
@RestController
public class VehicleSignalController {

    private final ProducerService vehicleSignalService;

    public VehicleSignalController(ProducerService vehicleSignalService) {
        this.vehicleSignalService = vehicleSignalService;
    }

    @RequestMapping(value = "/send-signal")
    public void vehicleSignal(@Valid @RequestBody VehicleSignal vehicleSignal) {
        vehicleSignalService.sendSignalToKafkaTopic(vehicleSignal);
    }

}
