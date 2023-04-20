package com.kh.baribari.user.controller;

import com.kh.baribari.user.domain.UserMyPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.common.JsonParse;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private JsonParse jsonParse;

//    로그인 뷰
    @GetMapping("login")
    public String loginView(){
        return "login/login";
    }

//    회원가입 뷰
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

//    회원가입 id 유효성 체크
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

//  회원가입 닉네임 유효성 체크 ajax
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

//    유저 회원가입 로직
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

//  판매자 회원가입 로직
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

//    유저-마이페이지 뷰
    @GetMapping("myPageUser")
    public String myPageUserView(
            Authentication authentication,
            Model model
    ){
        User user = returnUser(authentication);
        UserMyPageData userUserMyPageData = new UserMyPageData(user.getUserNo(), user.getUserLevelPoint());
        UserMyPageData userMyPageData = uService.selectUserMyPageData(userUserMyPageData);
        model.addAttribute("userMyPageData", userMyPageData);
        return "myPage/myPageUser";
    }

//    유저-마이페이지 수정 뷰
    @GetMapping("myPageUser/modify")
    public String myPageUserModifyView(
            Authentication authentication,
            Model model
    ){
        User user = returnUser(authentication);
        model.addAttribute("user",user);
        return "myPage/information/UserModify";
    }


    // 유저 수정 페이지
    @PostMapping("myPageUser/modifySubmit")
    @ResponseBody
    public String modifySubmit(@ModelAttribute User user){
        User updateByUser = uService.updateMyPageByUser(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails userDetails = (PrincipalDetails) auth.getPrincipal();
        userDetails.setUser(updateByUser);
        return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/modify';</script>";
    }

    // 배송지 관리
    @GetMapping("/myPageUser/address")
    public String myPageUserAddressView(Authentication authentication){
        User user = returnUser(authentication);


        return "myPage/information/Address";
    }

    // 세션에서 user 를 꺼내줌
    private User returnUser(Authentication authentication){
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }



}
