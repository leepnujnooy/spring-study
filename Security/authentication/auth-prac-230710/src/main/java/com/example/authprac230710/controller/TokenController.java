package com.example.authprac230710.controller;


import com.example.authprac230710.jwt.JwtRequestDTO;
import com.example.authprac230710.jwt.JwtTokenDTO;
import com.example.authprac230710.jwt.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
@Slf4j
@RequiredArgsConstructor
public class TokenController {

    private final JwtTokenUtils jwtTokenUtils;   //실제로 JWT 를 발급하기 위해 필요한 Bean
    private final UserDetailsManager userDetailsManager; //사용자 정보를 확인하기 위한 Bean
    private final PasswordEncoder passwordEncoder; //사용자가 JWT 발급을 위해 제출하는 비밀번호가 일치하는지 확인하기 위한 Bean


    @PostMapping("/issue")
    public JwtTokenDTO issueJWT(@RequestBody JwtRequestDTO request){
        JwtTokenDTO newToken = new JwtTokenDTO();
        return newToken;
    }
}
