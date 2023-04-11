package com.kh.baribari.user.user.controller;

import com.kh.baribari.common.JsonParse;
import com.kh.baribari.user.user.domain.User;
import com.kh.baribari.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private JsonParse jsonParse;

    
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
        return jsonParse.returnJson(result);
    }

    @GetMapping("ajaxNickNameCheck")
    @ResponseBody
    public String ajaxNickNameCheck(String nickName){
        User user = uService.selectNickNameCheck(nickName);
        String result;
        if (user != null) {
            result = "중복";
        }else {
            result = "사용가능";
        }
        return jsonParse.returnJson(result);
    }


}
