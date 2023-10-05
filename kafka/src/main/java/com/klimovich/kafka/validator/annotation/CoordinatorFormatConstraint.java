package com.klimovich.kafka.validator.annotation;

import jakarta.validation.Constraint;

import jakarta.validation.Payload;
import java.lang.annotation.*;

import com.klimovich.kafka.validator.CoordinatorFormatValidator;

@Documented
@Constraint(validatedBy = CoordinatorFormatValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CoordinatorFormatConstraint {
    String message() default "Invalid Coordinate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
