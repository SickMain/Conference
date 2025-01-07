package com.example.conference.exceptions;

public class ConferenceNotFoundException extends RuntimeException{
    public ConferenceNotFoundException(){
        super("Конференция не найдена");
    }
}
