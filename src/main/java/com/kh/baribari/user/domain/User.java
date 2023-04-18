package com.kh.baribari.user.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
public class User {
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userNickName;
    private String userEmail;
    private String userPhone;
    private Timestamp userTimestamp;
    private int userType;
    private int userLevel;
    private int userConform;
    private int userCount;
    private int userAble;
    private List<Role> roles;



}
