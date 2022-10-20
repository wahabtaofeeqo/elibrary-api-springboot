/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.services;

import com.example.elibrary.interfaces.IService;
import com.example.elibrary.models.Book;
import com.example.elibrary.repositories.BookRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author user
 */
@Service
public class BookService implements IService<Book>{
    
    @Autowired
    BookRepository repository;

    public Book get(String id) {
        return repository.findById(UUID.fromString(id)).get();
    }
    
    @Override
    public Book create(Book model) {
        return this.repository.save(model);
    }
}
