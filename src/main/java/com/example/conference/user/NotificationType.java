package com.example.conference.user;

public enum NotificationType {
    TEAM_INVITE ("Вы были приглашены в команду %s, пользователем %s");

    NotificationType(String message) {
        this.message = message;
    }

    final String message;
}
