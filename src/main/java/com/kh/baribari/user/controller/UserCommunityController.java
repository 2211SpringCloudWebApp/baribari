package com.kh.baribari.user.controller;

import com.kh.baribari.user.service.UserCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserCommunityController {
    @Autowired
    private UserCommunityService uCService;

    @GetMapping("/myPageUser/pegDown")
    public String pegDownView(){
        return "myPage/community/pegDown";
    }


}
