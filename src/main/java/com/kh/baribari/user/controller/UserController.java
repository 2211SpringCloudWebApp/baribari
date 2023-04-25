package com.kh.baribari.user.controller;

import com.kh.baribari.common.FileUpload;
import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.MyPageQna;
import com.kh.baribari.user.domain.UserMyPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kh.baribari.common.JsonParse;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private JsonParse jsonParse;

    @Autowired
    @Qualifier("fileUpload")
    private FileUpload fileUpload;

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

//  회원탈퇴 뷰
    @GetMapping("myPageUser/withdraw")
    public String withdrawView(){
        return "myPage/information/withdraw";
    }
//    회원탈퇴 로직
    @PostMapping("withdrawUser")
    @ResponseBody
    public String withdrawUser(int userNo){
        int result = uService.deleteUserByUserNo(userNo);
        if(result > 0){
            return "성공";
        }else {
            return "실패";
        }
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

    // 배송지 관리 뷰
    @GetMapping("myPageUser/address")
    public String myPageUserAddressView(Authentication authentication, Model model){
        User user = returnUser(authentication);
        List<Address> alist = uService.selectAddressList(user);
        model.addAttribute("aList", alist);
        return "myPage/information/Address";
    }

//    배송지 추가 컨트롤러
    @PostMapping("addAddress")
    @ResponseBody
    public String addAddressByUser(Authentication authentication, @ModelAttribute Address address){
        address.setUserNo(returnUser(authentication).getUserNo());
        int result = uService.insertAddressByUser(address);
        return "<script>alert('배송지가 추가되었습니다!'); location.href='/myPageUser/address';</script>";
    }

//   배송지 삭제
    @PostMapping("ajaxRemoveAddress")
    @ResponseBody
    public String ajaxRemoveAddress(int addressNo){
        int result = uService.deleteAddress(addressNo);
        if(result > 0){
            return jsonParse.returnJson("성공");
        }else {
            return jsonParse.returnJson("실패");
        }
    }
    
    // 세션에서 user 를 꺼내줌
    private User returnUser(Authentication authentication){
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

//    유저 1:1 문의 뷰
    @GetMapping("/myPageUser/qna")
    public String myPageUserQnaView(Authentication authentication,Model model,@RequestParam String qnaAnswerYn){
        User user = returnUser(authentication);
        MyPageQna qna = new MyPageQna(user.getUserNo(), qnaAnswerYn);
        List<MyPageQna> qnaList = uService.selectQna(qna);
        model.addAttribute("qnaList",qnaList);
        return "myPage/qna/qna";
    }

//    문의 상세페이지
    @GetMapping("/myPageUser/qnaDetail")
    public String myPageUserQnaDetailView(@RequestParam int qnaNo,Model model){
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail",qna);
        return "myPage/qna/qnaDetail";
    }
//    문의 삭제
    @PostMapping("/ajaxRemoveQna")
    @ResponseBody
    public String ajaxRemoveQna(int qnaNo){
        int result = uService.qnaRemove(qnaNo);
        if(result > 0) {
            return "성공";
        }else {
        return "실패";
        }
    }

//    문의 수정 뷰
    @GetMapping("/myPageUser/qnaModify")
    public String myPageUserQnaModify(@RequestParam int qnaNo,Model model){
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail",qna);
        return "myPage/qna/qnaModify";
    }

//    문의 수정 저장
    @PostMapping("/qnaModify/save")
    @ResponseBody
    public String myPageUserQnaModifySave(@ModelAttribute MyPageQna myPageQna, MultipartFile file, HttpServletRequest request){
        int result = uService.qnaModifySave(myPageQna);
        if(result > 0){
        return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        }else {
            return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        }
    }
//    문의 작성 뷰
    @GetMapping("/myPageUser/qnaWrite")
    public String myPageQnaWriteView(){
        return "myPage/qna/qnaWrite";
    }
//    문의 작성 로직
    @PostMapping("/qnaWrite/save")
    public String myPageQnaWriteSave(
            @RequestParam(name="qnaPic1") MultipartFile qnaPic1,
            @RequestParam(name="qnaPic2") MultipartFile qnaPic2,
            @RequestParam int userNo,
            HttpServletRequest request,
            @RequestParam String qnaContent
    ) throws Exception {
        String path = "myPageUser\\qna\\";
        String filePath1 = null, filePath2 = null;
        if(!qnaPic1.isEmpty()){
        Map<String,String> file = fileUpload.saveFile(qnaPic1,request,path);
        filePath1 = file.get("dbSavePath");
        }
        if(!qnaPic2.isEmpty()){
        Map<String,String> file2 = fileUpload.saveFile(qnaPic2,request,path);
            filePath2 = file2.get("dbSavePath");
        }

        MyPageQna myPageQna = new MyPageQna(qnaContent,userNo,filePath1,filePath2);
        int result = uService.qnaWrite(myPageQna);

        return "asd";
    }

//    주문배송조회 뷰
    @GetMapping("myPageUser/orderLogistic")
    public String orderLogisticView(){

        return "myPage/order/order-logistic";
    }
}
