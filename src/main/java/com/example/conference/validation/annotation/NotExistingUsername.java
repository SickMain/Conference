package com.example.conference.validation.annotation;

import com.example.conference.validation.validator.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistingUsername {
    String message() default "Пользователь с таким username уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
