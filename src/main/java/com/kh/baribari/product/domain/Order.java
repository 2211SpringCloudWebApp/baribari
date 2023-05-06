package com.kh.baribari.product.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Order {
	private int orderNo;
	private Date orderDate;
	private int orderDetailNo;
	private int orderDetailCount;
	private String orderLogis;
	private int orderTrackingNo;
	private int orderStatus;
	private int orderPrice;

	private int sellerUserNo;

	private int userNo;
	private String userId;
	private String userName;
	private String userEmail;
	private String addressName;
	private String addressZipCode;
	private String addressJibun;
	private String addressJibunDetail;
	private String addressPickup;
	private String addressPhone;
	
	private int productNo;
	private String productBrand;
	private String productName;
	private int productPrice;
	private int productDiscount;
	private int productDeliveryCharge;
}
