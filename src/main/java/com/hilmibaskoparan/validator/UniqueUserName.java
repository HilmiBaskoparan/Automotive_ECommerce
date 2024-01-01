package com.hilmibaskoparan.validator;

import com.hilmibaskoparan.validator.impl.UniqueUserNameImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {UniqueUserNameImpl.class})
public @interface UniqueUserName {
    String message() default "{0003}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
