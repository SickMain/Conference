package com.example.conference.exceptions;

public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException(){
        super("Команда не найдена");
    }
}
