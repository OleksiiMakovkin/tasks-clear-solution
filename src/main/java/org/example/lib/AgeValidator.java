package org.example.lib;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AgeValidator implements ConstraintValidator<ValidEmail, LocalDate> {
    @Value("${min.age")
    private int minAge;

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        int userAge = LocalDate.now().getYear() - localDate.getYear();
        return localDate.isBefore(localDate.now()) && userAge >= minAge;
    }
}
