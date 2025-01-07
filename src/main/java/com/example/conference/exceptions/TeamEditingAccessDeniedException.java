package com.example.conference.exceptions;

public class TeamEditingAccessDeniedException extends RuntimeException{
    public TeamEditingAccessDeniedException() {
        super("У вас нет доступа к редактированию этой команды");
    }
}
