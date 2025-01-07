/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.conference.repository.teams;

import com.example.conference.teams.UserInTeam;
import com.example.conference.teams.UserInTeamKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author user
 */
public interface UserInTeamRepository extends CrudRepository<UserInTeam, UserInTeamKey> {

    Optional<UserInTeam> findByActivationCode(String activationCode);
}
