package com.example.authprac230710.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenUtils {

    private final Key signingKey;
    private final JwtParser jwtParser; //JWT 번역기.

    //jwtSecret : application.yaml 파일 안에 있는 jwt - secret 의 String 값
    public JwtTokenUtils(@Value("${jwt.secret}")String jwtSecret){
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build();
    }


    //token 생성 method
    public String generateToken(UserDetails userDetails){

        //JWT 에 담고싶은 내용 추가. 내용들을 claims 라고 함
        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)));

        return Jwts.builder() //빌더 패턴
                .setClaims(jwtClaims) //토큰에 실어담을 클레임 넣기
                .signWith(signingKey) //사용할 Key 넣기
                .compact(); // .build() 와 같음
    }

}
