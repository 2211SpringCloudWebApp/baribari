package com.kh.baribari.admin.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserList {
    private int userNo;
    private String userId;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userPhone;
    private Timestamp userDate;
    private int userType;
    private int reportCount;
    private String userAble;
}
