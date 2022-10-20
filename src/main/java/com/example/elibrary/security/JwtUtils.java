/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.elibrary.security;

import com.example.elibrary.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author user
 */
@Component
public class JwtUtils {
    
    @Value("${jwt.secret}")
    private String secret;
    
    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token).getBody().getSubject();
    }
    
    public String generateToken(Authentication auth) {
        
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        
        Map<String, Object> claims = new HashMap<>();
                
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setSubject(details.getUsername())
                .setExpiration(new Date((new Date()).getTime() + 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token);
            return true;
        } 
        catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            System.err.println("Auth failed: " + e.getMessage());
        }
        return false;
    }
}
