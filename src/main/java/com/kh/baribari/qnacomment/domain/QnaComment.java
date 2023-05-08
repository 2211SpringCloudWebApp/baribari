package com.kh.baribari.qnacomment.domain;

import lombok.Data;

@Data
public class QnaComment {
	private int qnaNo;
	private int commentNo;
	private int productNo;
	private int userNo;
	private String userId;
	private String commentContent;
}
