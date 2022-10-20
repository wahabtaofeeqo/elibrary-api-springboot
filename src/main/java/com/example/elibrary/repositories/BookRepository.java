/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.repositories;

import com.example.elibrary.models.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface BookRepository extends JpaRepository<Book, UUID> {
    
}
