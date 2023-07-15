package com.cinema.galaxy.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<ValidEnumValue, String> {

    private Set<String> values;

    @Override
    public void initialize(ValidEnumValue constraintAnnotation){
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return  values.contains(value);
    }
}
