package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class Favorite {
    private String productPic1;
    private String productName;
    private int productPrice;
    private int productNo;
    private int productSales;
    private int userNo;

    public Favorite() {
    }

    public Favorite(int productNo, int userNo) {
        this.productNo = productNo;
        this.userNo = userNo;
    }
}
