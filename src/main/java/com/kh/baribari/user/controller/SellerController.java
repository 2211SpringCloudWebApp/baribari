package com.kh.baribari.user.controller;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.common.JsonParse;
import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerController {
    @Autowired
    private SellerService sService;

    @Autowired
    private JsonParse jsonParse;

    @Autowired
    @Qualifier("fileUpload")
    private FileInfo fileUpload;

    @Autowired
    private ReturnUser returnUser;

    @GetMapping("myPageSeller")
    public String myPageSeller(Authentication authentication){
        User user = returnUser.returnUser(authentication);


        return "myPageSeller/myPageSeller";
    }


}
