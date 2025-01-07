package com.example.conference.user;

import com.example.conference.teams.Team;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Notification  {

    public Notification() {
    }
    public Notification(NotificationType notificationType, User notificationReceiver, Team team,String activationCode) {
        this.message = String.format(notificationType.message,team.getName(),team.getLeader().getUsername());
        this.notificationReceiver = notificationReceiver;
        this.url = activationCode;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String url;

    private String message;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User notificationReceiver;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    public User getNotificationReceiver() {
        return notificationReceiver;
    }

    public void setNotificationReceiver(User notificationReceiver) {
        this.notificationReceiver = notificationReceiver;
    }
}
