/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.conferenceisu.repository;

import com.example.conferenceisu.forms.RegistrationForm;
import com.example.conferenceisu.model.UserInTeam;
import com.example.conferenceisu.model.UserInTeamKey;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.Role;
import com.example.conferenceisu.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author user
 */
public interface UserInTeamRepository extends CrudRepository<UserInTeam, UserInTeamKey> {
    
}
