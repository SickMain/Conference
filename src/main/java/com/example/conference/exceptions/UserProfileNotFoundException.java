package com.example.conference.exceptions;

public class UserProfileNotFoundException extends RuntimeException{

    public UserProfileNotFoundException(){
        super("Профиль пользователя не найден");
    }
}
