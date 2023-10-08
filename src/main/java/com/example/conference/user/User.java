/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conference.user;

import com.example.conference.forms.RegistrationForm;
import com.example.conference.model.Team;
import com.example.conference.model.UserInTeam;
import com.example.conference.repository.UserRepository;
import com.example.conference.user.Role;
import com.example.conference.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.hibernate.engine.internal.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author user
 */
@Entity
@Table(name = "WebUser")
public class User implements UserDetails, Serializable {


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.secondName, other.secondName)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public User() {
        this.id = null;
        this.firstName = null;
        this.secondName = null;
        this.email = null;
        this.password = null;
        this.username = null;
    }

    public User(Long id,String firstName, String secondName, String email, String password, String username) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.username = username;
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private final String firstName;
    
    private final String secondName;
    
    private final String username;
    
    private final String email;
    
    private final String password;
    
    private String profileImg;

    
    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            
            name = "web_user_roles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles;
    
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    Set <UserInTeam> userInTeam = new HashSet<>();

    @OneToMany(mappedBy = "leader",fetch = FetchType.EAGER)
    Set<Team> teams = new HashSet<>();
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setAuthorities(List<Role> roles) {
        this.roles = roles;
    }
            
    public String getEmail() {
        return email;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getFirstName() {
        return firstName;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Set<UserInTeam> getUserInTeam() {
        return userInTeam;
    }

    public void setUserInTeam(Set<UserInTeam> userInTeam) {
        this.userInTeam = userInTeam;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
