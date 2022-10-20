/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.requests;

import lombok.Data;
/**
 *
 * @author user
 */
@Data
public class UpdateBookDTO {
    
    private String title;
    private String author;
    private String description;
}
