/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conferenceisu.model;

import com.example.conferenceisu.forms.RegistrationForm;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.Role;
import com.example.conferenceisu.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author user
 */
@Embeddable
public class UserInTeamKey implements Serializable {

    public UserInTeamKey(User user, Team team) {
        this.userId = user.getId();
        this.teamId = team.getId();
    }
    
    @Column(name = "user_id")
    Long userId;
    
    @Column(name = "team_id")
    Long teamId;

    public UserInTeamKey() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.userId);
        hash = 83 * hash + Objects.hashCode(this.teamId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserInTeamKey other = (UserInTeamKey) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return Objects.equals(this.teamId, other.teamId);
    }
    
    
}
