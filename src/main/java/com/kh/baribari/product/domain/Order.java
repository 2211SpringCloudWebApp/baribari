package com.kh.baribari.product.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Order {
	private int orderNo;
	private int userNo;
	private Date orderDate;
	
	private int productNo;
	private int orderDetailNo;
	private int orderDetailCount;
	private String orderLogistics;
	private int orderTrackingNo;
	private int orderStatus;
	
	private int sellerUserNo;
}
