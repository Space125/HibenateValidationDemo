package com.example.validation.hibenatevalidationdemo.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Kurilov on 30.09.2019
 */
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> valueEnums = null;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        valueEnums = new ArrayList<>();

        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();

        @SuppressWarnings("rawtypes")
        Enum[] enumValArr = enumClass.getEnumConstants();

        for(@SuppressWarnings("rawtypes")
                Enum enumVal : enumValArr) {
            valueEnums.add(enumVal.toString().toUpperCase());
        }

    }


    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return valueEnums.contains(string);
    }
}
