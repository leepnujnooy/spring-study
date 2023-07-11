package com.example.prac230711.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class ViewController {

    @GetMapping("/login")
    public String loginView(){
        return "login";
    }
}
