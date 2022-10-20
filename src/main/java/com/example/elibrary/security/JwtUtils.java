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
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
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
        return Jwts.parserBuilder()
                .setSigningKey(generalKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    
    public String generateToken(Authentication auth) {
        
        UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(details.getUsername())
                .setExpiration(new Date((new Date()).getTime() + 86400000))
                .signWith(generalKey()).compact();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(generalKey()).build().parseClaimsJws(token);
            return true;
        } 
        catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            System.err.println("Auth failed: " + e.getMessage());
        }
        return false;
    }
    
    public Key generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secret);
        Key key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA512");
        return key;
    }
}
