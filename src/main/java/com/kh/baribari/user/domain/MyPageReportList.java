package com.kh.baribari.user.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MyPageReportList {
    private int reportNo;
    private String reportContent;
    private String reportCategoryName;
    private String reportTargetName;
    private String reportProcessing;
    private String reportDate;

}
