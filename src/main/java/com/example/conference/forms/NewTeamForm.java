/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.forms;

import com.example.conference.forms.RegistrationForm;
import com.example.conference.model.Team;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;
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
