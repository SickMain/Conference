/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.teams;

import com.example.conference.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.util.Set;


@Entity
public class UserInTeam {

    public UserInTeam(User user, Team team) {
        this.user = user;
        this.team = team;
        this.id = new UserInTeamKey(user, team);
        isActive = true;
    }
    
    @EmbeddedId
    private UserInTeamKey id;
    
    private Boolean isActive;

    private String activationCode;

    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    public UserInTeam() {
    }

    public UserInTeamKey getId() {
        return id;
    }

    public void setId(UserInTeamKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
    public Boolean isActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

}
