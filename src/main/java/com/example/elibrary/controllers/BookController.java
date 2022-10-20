/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.controllers;

import com.example.elibrary.models.Book;
import com.example.elibrary.models.User;
import com.example.elibrary.requests.AddBookDTO;
import com.example.elibrary.requests.UpdateBookDTO;
import com.example.elibrary.responses.OkResponse;
import com.example.elibrary.services.BookService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/books")
public class BookController extends BaseController {
    
    @Autowired
    BookService service;
    
    @GetMapping()
    public List<Object> list() {
        return null;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        Book book = this.service.get(id);
        return ResponseEntity.ok().body(new OkResponse("Book", book));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody UpdateBookDTO input) {
        
        /**
         * Confirm Book ID
         * 
         */
        Book book = this.service.get(id);
        
        
        //
        return ResponseEntity.ok().body(new OkResponse("Book updated successfully", getUser()));
    }
    
    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody AddBookDTO input) {
        
        /**
         * Add
         */
        Book book = input.toBook();
        book.setUser(getUser());
        
        //
        book = this.service.create(book);
        
        //
        return ResponseEntity.ok(new OkResponse("Book added successfully", book));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
}
