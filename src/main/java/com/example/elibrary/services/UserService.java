/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.services;

import com.example.elibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.elibrary.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author user
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository repository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    public User create(User model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return repository.save(model); 
    }
    
    public User findByUsername(String username) {
        return this.repository.findByUsername(username);
    }
}
