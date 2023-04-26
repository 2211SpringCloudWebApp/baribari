package com.kh.baribari.community.domain;

import lombok.Data;

@Data
public class HashTag {
	private int communityNo;	// 게시글 번호
	private String hashTagName;	// 해시태그 내용
}