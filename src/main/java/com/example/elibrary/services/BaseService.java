/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.services;

import com.example.elibrary.interfaces.IService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public class BaseService<T, E> implements IService<T> {
    
    private JpaRepository<T, E> repository;
    
    /**
     * 
     * @param data
     * @return 
     */
    @Override
    public T create(T data) {
        return this.repository.save(data);
    }
}
