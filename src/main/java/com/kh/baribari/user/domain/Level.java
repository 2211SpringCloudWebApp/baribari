package com.kh.baribari.user.domain;

import lombok.Data;

@Data
public class Level {
    private int levelNo;
    private int levelMinPoint;
    private int levelMaxPoint;

    public Level(int levelNo, int levelMinPoint) {
        this.levelNo = levelNo;
        this.levelMinPoint = levelMinPoint;
    }
}
