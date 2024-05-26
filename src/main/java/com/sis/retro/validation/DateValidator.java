package com.sis.retro.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Slf4j
public class DateValidator implements ConstraintValidator<DateValidatorConstraint, String> {


    @Override
    public void initialize(DateValidatorConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            log.info("date parsing is successful for the date {}", date);
        } catch (DateTimeParseException exception) {
            log.info("unable to parse date {} with error {}", date,exception.getMessage());
            return false;
        }
        return true;
    }
}