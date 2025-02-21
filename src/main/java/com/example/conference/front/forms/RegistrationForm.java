/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.front.forms;

import com.example.conference.user.User;
import com.example.conference.validation.annotation.NotExistingEmail;
import com.example.conference.validation.annotation.NotExistingUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegistrationForm {

    public RegistrationForm(String firstName, String secondName, String email, String password, String username) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public RegistrationForm() {
    }
    
    @NotBlank(message = "Введите имя")
    private String firstName;
    
    @NotBlank(message = "Введите фамилию")
    private String secondName;
    
    @NotBlank(message = "Введите действительный почтовый адрес")
    @Email
    @NotExistingEmail
    private String email;
   
    @Size(min = 5, message = "Пароль должен состоять минимум из 5 символов")
    private String password;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @NotExistingUsername
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public User toUser(String password){
        return new User(0L,firstName,secondName,email,password,username);
    }
}
