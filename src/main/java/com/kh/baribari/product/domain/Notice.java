package com.kh.baribari.product.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Notice {
	private int noticeNo;
	private String noticeCategory;
	private String noticeTitle;
	private String userId;
	private String noticeContent;
	private Date noticeDate;
	private Date noticeUpdate;
	
}
