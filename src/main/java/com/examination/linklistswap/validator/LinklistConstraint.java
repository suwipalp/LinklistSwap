package com.examination.linklistswap.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = LinklistValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface LinklistConstraint {
    String regexp() default "^((\\d+)->)+(\\d+)$";

    String message() default "linklist string format is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
