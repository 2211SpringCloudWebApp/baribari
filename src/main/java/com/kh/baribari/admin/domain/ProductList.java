package com.kh.baribari.admin.domain;

import lombok.Data;

@Data
public class ProductList {
    private int productNo;
    private String productName;
    private String userNickname;
    private String productBrand;
    private int productSales;
}
