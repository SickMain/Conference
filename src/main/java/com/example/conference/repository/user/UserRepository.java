/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.conference.repository.user;


import java.util.Optional;

import com.example.conference.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author user
 */
public interface UserRepository extends JpaRepository<User,Long> {

     Optional<User> findByEmail(String email);

     Optional<User> findByUsername(String username);

     @Query("select u.profileImg from User u where u.username = :username")
     Optional<String> findProfileImgByUsername(@Param("username") String username);


}
