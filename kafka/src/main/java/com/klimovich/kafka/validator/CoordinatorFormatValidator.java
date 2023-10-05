package com.klimovich.kafka.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinatorFormatValidator implements ConstraintValidator<CoordinatorFormatConstraint, Double> {

    @Override
    public void initialize(CoordinatorFormatConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double coordinate, ConstraintValidatorContext constraintValidatorContext) {
        return coordinate != null && coordinate.toString().matches("-?[1-9][0-9]*(\\.[0-9]+)?");
    }
}
