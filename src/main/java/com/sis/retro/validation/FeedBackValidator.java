package com.sis.retro.validation;


import com.sis.retro.model.FeedBackType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


@Slf4j
public class FeedBackValidator implements ConstraintValidator<FeedbackTypeConstraint, String> {


    @Override
    public void initialize(FeedbackTypeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        log.info("validating feedback type {}", type);
        return Arrays.stream(FeedBackType.values()).anyMatch(each->each.toString().equals(type));
    }
}