/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.conferenceisu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author user
 */
@Entity
@Table(name = "Conference")
public class Conference {

    public Conference(Long id, String name, String description, Boolean isIndividual) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isIndividual = isIndividual;
    }

    protected Conference() {
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    @Column(columnDefinition = "varchar(550)")
    private String description;
    
    private Boolean isIndividual;
    
    //@ManyToMany
    //private List<WebUser> users;
    
    
    public Boolean getIsIndividual() {
        return isIndividual;
    }

    public void setIsIndividual(Boolean isIndividual) {
        this.isIndividual = isIndividual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
