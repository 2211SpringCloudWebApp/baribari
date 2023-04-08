package com.kh.baribari.user.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.baribari.user.user.domain.User;
import com.kh.baribari.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    public String returnJson(String result){
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return jsonString;
        }

        return jsonString;
    }
    
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

    @GetMapping("ajaxCheckId")
    @ResponseBody
    public String ajaxIdCheck(String id){
        User user = uService.selectIdCheck(id);
        String result;
        if(user != null) {
            result = "중복";
        }else {
            result = "사용가능";
        }
        return returnJson(result);
    }

}
