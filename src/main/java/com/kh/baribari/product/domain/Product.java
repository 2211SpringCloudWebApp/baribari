package com.kh.baribari.product.domain;

import lombok.Data;

@Data
public class Product {
	private int productNo;
	private String productCategory;
	private String productBrand;
	private String productName;
	private int productPrice;
	private String productContent;
	private String productMdYn;
	private int productSales;
	private String productHashTag;
	private int productDiscount;
	private int sellerUserNo;
	private String productPic1;
	private String productPic2;
	private String productPic3;
	private String productPic4;
}
