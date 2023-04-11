package com.kh.baribari.user.user.controller;

import com.kh.baribari.common.JsonParse;
import com.kh.baribari.user.user.domain.User;
import com.kh.baribari.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("registerSeller")
    public String registerSellerView(){
        return "login/registerSeller";
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


    @PostMapping("registerUserProc")
    @ResponseBody
    public String registerUserProc(@ModelAttribute User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int result = uService.insertUserByUser(user);
        if(result > 0 ){
            return "<script>alert('"+ user.getUserNickName() +"님 환영합니다!'); location.href='/';</script>";
        }else {
            return "<script>alert('회원가입에 실패하였습니다. \n 관리자에게 문의하시거나 다시 시도해주세요.'); location.href='/register';</script>";
        }
    }

    @PostMapping("registerSellerProc")
    @ResponseBody
    public String registerSellerProc(@ModelAttribute User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int result = uService.insertUserBySeller(user);
        if(result > 0){
            return "<script>alert('"+ user.getUserNickName() +"님 환영합니다!'); location.href='/';</script>";
        }else {
            return "<script>alert('회원가입에 실패하였습니다. \n 관리자에게 문의하시거나 다시 시도해주세요.'); location.href='/register';</script>";
        }
    }


}
