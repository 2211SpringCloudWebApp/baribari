package com.kh.baribari.product.domain;

import lombok.Data;

@Data
public class Payment {
	private String merchantUid;
	private int userNo;
	private int productNo;
	private int payVal;
	private int payDiscount;
	private int orderDetailNo;
}
