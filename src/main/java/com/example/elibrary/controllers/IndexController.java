/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.controllers;

import com.example.elibrary.responses.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
public class IndexController {
    
    @GetMapping("/")
    public ResponseEntity<BaseResponse> welcome() {
        return ResponseEntity.ok().body(
                new BaseResponse(true, "Are you ready to consume the API? Let's get started")
        );
    }
}
