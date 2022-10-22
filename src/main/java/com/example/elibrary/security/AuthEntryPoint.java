/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.security;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.elibrary.responses.ErrResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author user
 */
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
     
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrResponse res = new ErrResponse("Unauthorized");

        OutputStream os = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(os, res);

        //
        os.flush();
    }
    
}
