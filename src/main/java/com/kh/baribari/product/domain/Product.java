package com.kh.baribari.product.domain;

import lombok.Data;

@Data
public class Product {

	private int productNo;
	private String productName;
	private int productPrice;
	private String productContent;
	private String productMdYn;
	private int productSales;
	private String productHashTag;
	private int productDiscount;
	private int sellerUserNo;
	
}
