package com.kh.baribari.community.comment.service;

import java.util.List;

import com.kh.baribari.community.domain.Comment;

public interface CommentService {

	/**
	 * 댓글 등록
	 * @param comment
	 * @return
	 */
	public int commentRegister(Comment comment);

	/**
	 * 댓글 출력
	 * @param communityNo
	 * @return
	 */
	public List<Comment> commentShow(Integer communityNo);

}
