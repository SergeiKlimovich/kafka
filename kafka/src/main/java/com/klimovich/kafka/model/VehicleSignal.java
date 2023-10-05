package com.klimovich.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.klimovich.kafka.validator.annotation.CoordinatorFormatConstraint;
import com.klimovich.kafka.validator.annotation.TaxiIdConstraint;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleSignal {

    @TaxiIdConstraint
    private String id;

    @CoordinatorFormatConstraint
    private Double latitude;

    @CoordinatorFormatConstraint
    private Double longitude;
}
