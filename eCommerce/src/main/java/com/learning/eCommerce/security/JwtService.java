package com.learning.eCommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private String SECRET =
            "abcdefghijklmnopqrstuvwxyz123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET
                )
                .compact();
    }

//    extractUsername()
public String extractUsername(String token){

}
//    extractExpiration()
//
//    isTokenExpired()
//
//    validateToken()

}