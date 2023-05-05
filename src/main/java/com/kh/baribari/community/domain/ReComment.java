package com.kh.baribari.community.domain;

import lombok.Data;

@Data
public class ReComment {
	private int userNo;
	private String reCommentSubject;
	private int reCommentNo;
	private String reCommentDate;
	private int communityNo;
	private int commentNo;
}
