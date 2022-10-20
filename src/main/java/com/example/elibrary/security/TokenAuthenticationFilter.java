/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.security;

import com.example.elibrary.services.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author user
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    UserDetailsServiceImpl serviceImpl;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        /**
         * Extract Token
         */
        String token = getToken(request);
        
        if(token != null && jwtUtils.validateToken(token)) {
            
            /**
             * Get username
             */
            String username = jwtUtils.getUsername(token);
            
            /**
             * Get user details
             */
            UserDetails details = serviceImpl.loadUserByUsername(username);
            
            /**
             * Create Auth
             */
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            /**
             * Update Context
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        
        //
        doFilter(request, response, filterChain);
    }
    
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring("Bearer".length(), header.length()).trim();
        }
        
        return null;
    }
}
