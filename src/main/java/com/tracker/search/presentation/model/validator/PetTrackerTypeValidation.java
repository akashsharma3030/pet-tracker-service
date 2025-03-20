package com.tracker.search.presentation.model.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PetTrackerTypeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PetTrackerTypeValidation {
    String message() default "Invalid Pet and Tracker Type provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
