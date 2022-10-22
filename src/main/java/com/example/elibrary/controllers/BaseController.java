/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.controllers;

import com.example.elibrary.models.User;
import com.example.elibrary.responses.OkResponse;
import com.example.elibrary.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author user
 */
public class BaseController {
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl detailsImpl = (UserDetailsImpl) auth.getPrincipal();
        return detailsImpl.getUser();
    }

    public ResponseEntity<?> okResponse(String message, Object data) {
        return ResponseEntity.ok().body(new OkResponse(message, data));
    }
}
