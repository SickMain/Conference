/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conferenceisu.forms;

import com.example.conferenceisu.forms.RegistrationForm;
import com.example.conferenceisu.model.Team;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.Role;
import com.example.conferenceisu.user.User;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author user
 */
public class NewTeamForm {

    public NewTeamForm() {
    }
    
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Team toTeam(){
        return new Team(0L,name);
    }
}
