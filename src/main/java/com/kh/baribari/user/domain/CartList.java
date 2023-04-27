package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class CartList {
    private String productName;
    private String userNickname;
    private String productPic1;
    private int productPrice;
    private int productNo;
    private int productQuantity;
    private int userNo;
    private int productDeliveryCharge;

    public CartList() {
    }

    public CartList(int productNo, int productQuantity, int userNo) {
        this.productNo = productNo;
        this.productQuantity = productQuantity;
        this.userNo = userNo;
    }

    public CartList(int productNo, int userNo) {
        this.productNo = productNo;
        this.userNo = userNo;
    }
}
