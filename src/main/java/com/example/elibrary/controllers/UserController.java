package com.example.elibrary.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    
    @GetMapping()
    public ResponseEntity<?> index() {
        return this.okResponse("My Profile", getUser());
    }
}
