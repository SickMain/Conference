/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.conference.repository;

import com.example.conference.forms.RegistrationForm;
import com.example.conference.model.UserInTeam;
import com.example.conference.model.UserInTeamKey;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface UserInTeamRepository extends CrudRepository<UserInTeam, UserInTeamKey> {
    
}
