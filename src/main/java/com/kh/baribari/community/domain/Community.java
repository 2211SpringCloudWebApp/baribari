package com.kh.baribari.community.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Community {
	private int communityNo;
	private String communitySubject;
	private String communityContent;
	private int communityCategory;
	private String communityDate;
	private int mapX;
	private int mapY;
	private String communityHashTag;
	private Timestamp communityYn;
	private int userNo;
}
