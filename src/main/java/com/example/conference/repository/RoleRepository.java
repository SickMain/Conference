/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.conferenceisu.repository;

import com.example.conferenceisu.forms.RegistrationForm;
import com.example.conferenceisu.repository.UserRepository;
import com.example.conferenceisu.user.Role;
import com.example.conferenceisu.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    
}
