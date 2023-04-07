package com.kh.baribari.user.user.controller;

import com.kh.baribari.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    @GetMapping("login")
    public String loginView(){
        return "login/login";
    }

    @GetMapping("register")
    public String registerView(){
        return "login/register";
    }

    @GetMapping("registerUser")
    public String registerUserView(){
        return "login/registerUser";
    }

}
