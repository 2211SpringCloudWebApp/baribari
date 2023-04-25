package com.kh.baribari.user.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class MyPageOrderList {
    private String productName;
    private String productPic1;
    private int orderNo;
    private Date orderDate;
    private int orderDetailCount;
    private String orderLogis;
    private int orderTrackingNo;
    private int orderStatus;
    private int orderPrice;
    private String userNickname;
    private int userNo;
}
