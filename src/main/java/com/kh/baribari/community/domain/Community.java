package com.kh.baribari.community.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Community {
	private int communityNo;			// 게시글 번호
	private String communitySubject;	// 게시글 제목
	private String communityContent;	// 게시글 내용
	private int communityCategory;		// 게시글 말머리
	private String communityDate;		// 게시글 작성날짜
	private int mapX;					// 지도 X좌표
	private int mapY;					// 지도 Y좌표
	private String communityYn;			// 블라인드 여부 0=정상, 1=블라인드
	private int userNo;					// 작성자 유저넘버
	private int viewCount;				// 조회수
	private int likeCount;				// 좋아요수
}