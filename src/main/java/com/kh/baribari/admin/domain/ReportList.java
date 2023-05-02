package com.kh.baribari.admin.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReportList {
    private int reportNo;
    private String reportContent;
    private int reportCategoryNo;
    private int reportUser;
    private String reportProcessing;
    private int reportTargetNo;
    private String reportTargetName;
    private String userNickName;
    private String reportCategoryName;
    private Timestamp reportDate;
    private int reportCount;

    public ReportList() {
    }

    public ReportList(int reportNo, int reportTargetNo) {
        this.reportNo = reportNo;
        this.reportTargetNo = reportTargetNo;
    }
}
