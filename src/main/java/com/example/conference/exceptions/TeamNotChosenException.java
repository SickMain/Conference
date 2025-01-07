package com.example.conference.exceptions;

public class TeamNotChosenException extends RuntimeException{
    private final Long conferenceId;
    public TeamNotChosenException(Long conferenceId) {
        super("Выберите команду");
        this.conferenceId = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }
}
