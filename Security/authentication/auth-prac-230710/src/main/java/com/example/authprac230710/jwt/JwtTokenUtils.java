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


    //jwt token 생성 method
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

    //jwt token 유효성 체크 method (jwt 를 해석하는 과정에서 유효하지않으면 예외발생)
    public boolean validate(String token){
        try{

            //parseClaimsJwt() : 암호화된 jwt 를 해석하는 method
            jwtParser.parseClaimsJwt(token);
            return true;

        }catch (Exception e){

            //parseClaimsJwt() 실행 오류시 jwt 의 유효성 입증 실패
            return false;

        }
    }

    //jwt token 을 인자로 받고, 그 토큰을 해석하여 사용자 정보를 회수하는 메서드
    public Claims parseClaims(String token){
        return jwtParser
                .parseClaimsJwt(token)
                .getBody();
    }
}
