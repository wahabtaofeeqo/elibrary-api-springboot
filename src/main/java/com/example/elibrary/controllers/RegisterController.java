/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.controllers;

import com.example.elibrary.models.User;
import com.example.elibrary.requests.LoginDTO;
import com.example.elibrary.requests.RegisterDTO;
import com.example.elibrary.responses.OkResponse;
import com.example.elibrary.security.JwtUtils;
import com.example.elibrary.services.UserDetailsImpl;
import com.example.elibrary.services.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("auth")
public class RegisterController {
    
    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    UserService service;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public ResponseEntity<OkResponse> register(@Valid @RequestBody RegisterDTO dto) {
        
        /**
         * Store user
         * 
         */
        User user = service.create(dto.toUser());
        
        //
        return ResponseEntity.ok().body(new OkResponse(true, "Account created successfully", user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        
        /**
         * Auth
         */
        Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        
        /**
         * Update context
         */
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetailsImpl detailsImpl = (UserDetailsImpl) auth.getPrincipal();
        
        /**
         * Token
         */
        String token = jwtUtils.generateToken(auth);
        
        Map<String, Object> data = new HashMap<>();
        data.put("user", detailsImpl.getUser());
        data.put("token", token);
        
        //
        return ResponseEntity.ok().body(new OkResponse("Login successful", data));
    }
}
