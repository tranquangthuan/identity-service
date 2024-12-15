package com.thuan.identiy_service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DobConstraintValidator.class})
public @interface DobConstraint {
    String message() default "Nhap tuoi khong hop le, tuoi phai lon hon {min}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min();
}
