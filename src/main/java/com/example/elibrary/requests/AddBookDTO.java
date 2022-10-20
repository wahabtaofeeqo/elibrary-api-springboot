/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.requests;

import com.example.elibrary.models.Book;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class AddBookDTO {
    
    @NotBlank(message = "Author name is required")
    private String author;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    public Book toBook() {
        return new Book(author, title, description);
    }
}
