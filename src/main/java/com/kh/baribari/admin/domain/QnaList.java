package com.kh.baribari.admin.domain;

import lombok.Data;

@Data
public class QnaList {
    private String userNickname;
    private int qnaNo;
    private String qnaContent;
    private String qnaDate;
    private String qnaAnswerYn;
    private String answerContent;
    private int answerNo;
    private String qnaPic1;
    private String qnaPic2;
    private int answerUserNo;
}
