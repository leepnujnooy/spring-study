package com.example.prac230711.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtils {
    private final Key signingKey;
    private final JwtParser jwtParser;

    JwtTokenUtils(@Value("${jwt.secret}")String jwtSecret){

        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();
    }

    //CREATE TOKEN
    public String createToken(UserDetails userDetails){

        //JWT 에 담을 데이터 만들기
        Claims claims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(1800)));

        //RETURN String TYPE
        return Jwts.builder()
                .addClaims(claims)
                .compact();
    }

    //TOKEN VALIDATE
    public boolean validateToken(String token){
        try{
            jwtParser.parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    //PARSING VALUE
    public Claims parseClaims(String token){
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

}
