package com.kh.baribari.user.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class MyPageOrderList {
    private int productNo;
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
    private String startDate;
    private String endDate;
    private String productBrand;

    public MyPageOrderList() {
    }

    public MyPageOrderList(int userNo, String startDate, String endDate) {
        this.userNo = userNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
