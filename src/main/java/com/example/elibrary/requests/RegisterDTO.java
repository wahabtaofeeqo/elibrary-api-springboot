/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.requests;

import com.example.elibrary.models.User;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class RegisterDTO {
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    @Size(min = 6)
    private String password;
    
    public User toUser() {
        return new User(name, email, password);
    }
}
