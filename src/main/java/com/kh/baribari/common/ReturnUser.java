package com.kh.baribari.common;

import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ReturnUser {
    // 세션에서 user 를 꺼내줌
    public User returnUser(Authentication authentication) {
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
