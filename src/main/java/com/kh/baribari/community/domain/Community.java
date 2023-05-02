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
	private double mapX;				// 지도 X좌표
	private double mapY;				// 지도 Y좌표
	private String communityYn;			// 블라인드 여부 0=정상, 1=블라인드
	private int userNo;					// 작성자 유저넘버
	private int viewCount;				// 조회수
	private String userNickname;		// 유저닉네임
	private int likeCount;				// 좋아요 수
	private int sort;					// 정렬을 위한 변수
	private int check;					// 검색 체크박스(제목 = 0 / 내용 = 1 / 닉네임 = 2)
	private String keyword;				// 검색어를 저장할 변수
}