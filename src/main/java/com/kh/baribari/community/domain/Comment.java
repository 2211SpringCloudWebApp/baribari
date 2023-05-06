package com.kh.baribari.community.domain;

import lombok.Data;

@Data
public class Comment {
	private int userNo;				// 작성한 유저번호
	private int communityNo;		// 게시글 번호
	private int commentNo;			// 댓글 번호
	private String commentContent;	// 댓글 내용
	private String commentDate;		// 댓글 작성일
	private String userNickname;	// 작성자 닉네임
	private int parentsCommentNo;	// 부모 댓글 번호
}
