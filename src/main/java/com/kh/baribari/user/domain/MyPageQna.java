package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class MyPageQna {
    private int qnaNo;
    private String qnaContent;
    private int qnaCategory;
    private int userNo;
    private int productNo;
    private int categoryNo;
    private String qnaDate;
    private String qnaAnswerYn;
    private int answerNo;
    private String answerContent;
    private int answerUserNo;
    private String qnaPic1;
    private String qnaPic2;

    public MyPageQna() {
    }

    public MyPageQna(int userNo, String qnaAnswerYn){
        this.userNo = userNo;
        this.qnaAnswerYn = qnaAnswerYn;
    }

    public MyPageQna(String qnaContent, int userNo, String qnaPic1, String qnaPic2) {
        this.qnaContent = qnaContent;
        this.userNo = userNo;
        this.qnaPic1 = qnaPic1;
        this.qnaPic2 = qnaPic2;
    }
}
