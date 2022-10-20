/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    public User() {
    }
     
    public User(String name, String email, String password) {
        this.name = name;
        this.username = email;
        this.password = password;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column
    private String name;
    
    @Column(unique = true)
    private String username;
    
    @Column
    @JsonIgnore
    private String password;
}
