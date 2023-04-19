package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class UserMyPageData {
    private int levelNo;
    private int levelMinPoint;
    private int levelMaxPoint;
    private int pointVal;
    private int payment;
    private int ready;
    private int logistic;
    private int complete;

    public UserMyPageData(int levelNo, int levelMinPoint) {
        this.levelNo = levelNo;
        this.levelMinPoint = levelMinPoint;
    }
}
