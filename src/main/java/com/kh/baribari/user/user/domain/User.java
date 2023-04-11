package com.kh.baribari.user.user.domain;

import lombok.Data;

import java.sql.Timestamp;

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
    private String userRole;



}
