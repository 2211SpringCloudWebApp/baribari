package com.kh.baribari.product.domain;

import lombok.Data;

@Data
public class Review {
	private int reviewNo;
	private String reviewContent;
	private int productNo;
	private int userNo;
	private String userName;
	private String reviewPic1;
	private String reviewPic2;
	private String reviewPic3;
	private String reviewPic4;
	private String reviewPic5;
}
