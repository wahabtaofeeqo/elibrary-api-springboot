/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.services;

import com.example.elibrary.interfaces.IService;
import com.example.elibrary.models.Book;
import com.example.elibrary.models.User;
import com.example.elibrary.repositories.BookRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<Book> paginate(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("title").ascending());
        return this.repository.findAll(pageable).toList();
    }

    public List<Book> loadUserBooks(User user, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("title").ascending());
        return this.repository.findAllByUser(user, pageable);
    }

    public List<Book> loadAuthorBooks(String author, int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("title").ascending());
        return this.repository.findAllByAuthor(author, pageable);
    }
}
