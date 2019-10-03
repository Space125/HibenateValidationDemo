package com.example.validation.hibenatevalidationdemo.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

/**
 * @author Ivan Kurilov on 03.10.2019
 */
@Documented
@Constraint(validatedBy = DateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface DateValidator {

    String message() default "Value is not valid date period it should be 2800...3019";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
