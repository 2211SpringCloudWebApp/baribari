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

    public MyPageQna() {
    }

    public MyPageQna(int userNo, String qnaAnswerYn){
        this.userNo = userNo;
        this.qnaAnswerYn = qnaAnswerYn;
    }
}
