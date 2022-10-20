/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class LoginDTO {
    
    @NotBlank
    @Email
    private String username;
    
    @NotBlank(message = "Password can not be empty")
    @Size(min = 6)
    private String password;
}
