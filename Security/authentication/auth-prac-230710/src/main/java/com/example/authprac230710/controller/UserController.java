package com.example.authprac230710.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users") //해당 컨트롤러는 /users url 부터 시작
@RequiredArgsConstructor //생성자 주입
public class UserController {

    private final UserDetailsManager userDetailsManager; //UserService 에서 구현한 메소드를 사용하기위한 인터페이스

    @GetMapping
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }


}
