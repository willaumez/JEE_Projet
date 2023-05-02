package com.example.control_hospital.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping(path = "/noAutorized")
    public String noAutorized(){
        return "noAutorized";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "loginUser";
    }
}
