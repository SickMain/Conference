/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.example.conference.repository.teams;

import com.example.conference.teams.Team;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author user
 */
public interface TeamsRepository extends CrudRepository<Team, Long> {
    
     List<Team> findDistinctByUserInTeam_UserId(Long id);

     List<Team> findByLeader_Id(Long id);

     @Query("SELECT t from Team t join t.userInTeam uit where uit.user.username = :username")
     List<Team> findByUsername(@Param("username") String username);
}
