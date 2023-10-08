/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.conferenceisu.repository;


import java.util.List;
import java.util.Optional;

import com.example.conferenceisu.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface UserRepository extends JpaRepository<User,Long> {

    
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsername(String username);

    public void removeUserByUsername(String username);

    //public List<User> findDistinctBy
}
