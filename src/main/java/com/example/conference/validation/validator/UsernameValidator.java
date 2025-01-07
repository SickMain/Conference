package com.example.conference.validation.validator;

import com.example.conference.service.UserService;
import com.example.conference.validation.annotation.NotExistingUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements
        ConstraintValidator<NotExistingUsername,String> {

    private final UserService userService;

    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext cVC) {
        return userService.findUserByUsername(s).isEmpty();
    }

}
