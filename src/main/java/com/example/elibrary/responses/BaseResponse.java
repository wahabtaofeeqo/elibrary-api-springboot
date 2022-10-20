/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.responses;

import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class BaseResponse {
    
    private boolean status;
    private String message;

    public BaseResponse() {
    }
     
    public BaseResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    } 
}
