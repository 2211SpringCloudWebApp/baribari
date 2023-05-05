package com.kh.baribari.user.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentList {
    private int commentNo;
    private int communityNo;
    private int userNo;
    private String commentContent;
    private String communitySubject;
    private Timestamp commentDate;
}
