package com.example.conference.validation.annotation;

import com.example.conference.validation.validator.EmailValidator;
import com.example.conference.validation.validator.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotExistingEmail {


    String message() default "Пользователь с таким Email уже существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
