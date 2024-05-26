package com.sis.retro.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface DateValidatorConstraint {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
