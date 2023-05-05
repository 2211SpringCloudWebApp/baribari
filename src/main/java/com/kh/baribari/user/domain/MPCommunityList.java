package com.kh.baribari.user.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MPCommunityList {
    private int communityNo;
    private String communitySubject;
    private String communityDate;
    private int communityCategory;
    private Timestamp scrapDate;
    private int ScrapNo;


}
