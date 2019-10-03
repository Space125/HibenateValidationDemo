package com.example.validation.hibenatevalidationdemo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * @author Ivan Kurilov on 03.10.2019
 */
public class DateValidatorImpl implements ConstraintValidator<DateValidator, Date> {

    @Override
    public void initialize(DateValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        long beginPeriodDate = 26192235600000L;
        long endPeriodDate = 33134734799000L;

        return date.getTime() > beginPeriodDate && date.getTime() < endPeriodDate;
    }
}
