package com.kh.baribari.community.domain;

import lombok.Data;

@Data
public class Comment {
	private int userNo;
	private int communityNo;
	private int commentNo;
	private String commentContent;
	private String commentDate;
	private String userNickname;
}
