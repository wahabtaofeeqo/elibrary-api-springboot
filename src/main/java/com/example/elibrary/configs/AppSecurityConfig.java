/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.configs;

import com.example.elibrary.security.AuthEntryPoint;
import com.example.elibrary.security.TokenAuthenticationFilter;
import com.example.elibrary.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author user
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    AuthEntryPoint authEntryPoint;
    
    @Bean
    TokenAuthenticationFilter authenticationFilter() {
        return new TokenAuthenticationFilter();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager(); 
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /**
         * CORS and CSFR
         * 
         */
        http.csrf().disable()
                .cors().disable();
        
        /**
         * Auth Error
         * 
         */
        http.exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        /**
         * Open routes
         * 
         */
        http.authorizeRequests().antMatchers("/", "/auth/**").permitAll();
              
       
        /**
         * Closed routes
         * 
         */
        http.authorizeRequests().anyRequest().authenticated();
        
        // Filter
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    } 
}
