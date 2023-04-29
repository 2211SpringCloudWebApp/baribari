package com.kh.baribari.admin.domain;

import lombok.Data;

@Data
public class QnaList {
    private String userNickname;
    private int qnaNo;
    private String qnaContent;
    private String qnaDate;
    private String qnaAnswerYn;
}
