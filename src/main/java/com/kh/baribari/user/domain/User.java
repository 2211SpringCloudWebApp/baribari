package com.kh.baribari.user.domain;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data @Builder
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
    private int userLevelPoint;
    private int userConform;
    private int userCount;
    private String userAble;
    private List<Role> roles;
    private String profilePicPath;


}
