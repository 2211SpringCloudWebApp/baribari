package com.kh.baribari.qna.domain;

import lombok.Data;

@Data
public class QnA {
	private int qnaNo;
	private String qnaContent;
	private int qnaCategory;
	private int userNo;
	private int productNo;
	private int categoryNo;
	private String qnaDate;
	private String qnaAnswerYn;
	private int qnaAnswerNo;
	private String qnaPic1;
	private String qnaPic2;
	// QnA Comment부분
	private int commentNo;
	private String userId;
	private String commentContent;
}
