package com.example.conference.exceptions;

public class TeamMembersAlreadyRegisteredException extends RuntimeException{

    private Long conferenceId;
    public TeamMembersAlreadyRegisteredException(Long conferenceId){
        super("Один или несколько членов команды уже зарегистрированы на мероприятие");
        this.conferenceId = conferenceId;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

}
