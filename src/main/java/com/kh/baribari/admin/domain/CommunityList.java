package com.kh.baribari.admin.domain;

import lombok.Data;

@Data
public class CommunityList {
    private int communityNo;
    private String communitySubject;
    private String userNickname;
    private String communityDate;
    private String communityCategoryName;
    private int viewCount;

}
