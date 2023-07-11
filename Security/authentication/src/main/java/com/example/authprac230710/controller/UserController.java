package com.example.authprac230710.controller;


import com.example.authprac230710.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/users") //해당 컨트롤러는 /users url 부터 시작
@RequiredArgsConstructor //생성자 주입
public class UserController {

    private final UserDetailsManager userDetailsManager; //UserService 에서 구현한 메소드를 사용하기위한 인터페이스
    private final PasswordEncoder passwordEncoder; //password 를 암호화 해주는 인터페이스

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/main")
    public String mainPage(Model model,Authentication authentication) {
        model.addAttribute("username",authentication.getName());
        return "main";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpRequest(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck){
        if(passwordCheck.equals(password)){
            userDetailsManager.createUser(CustomUserDetails.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .build());
            return "redirect:/users/login";
        }
        return "redirect:/users/register?error";
    }
}
