package com.klimovich.kafka.validator.annotation;

import jakarta.validation.Constraint;



import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.klimovich.kafka.validator.TaxiIdValidator;

@Documented
@Constraint(validatedBy = TaxiIdValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaxiIdConstraint {
    String message() default "Invalid Taxi Id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
