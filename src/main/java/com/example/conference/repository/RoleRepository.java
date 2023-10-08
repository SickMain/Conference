/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.conference.repository;

import com.example.conference.forms.RegistrationForm;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    
}
