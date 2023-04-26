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
}
