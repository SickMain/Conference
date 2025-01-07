package com.example.conference.validation.validator;

import com.example.conference.service.UserService;
import com.example.conference.validation.annotation.NotExistingEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<NotExistingEmail,String> {

    private final UserService userService;

    public EmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findUserByEmail(s).isEmpty();
    }

    @Override
    public void initialize(NotExistingEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
