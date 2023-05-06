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

	/**
	 * 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	public int commentDelete(Integer commentNo);

	/**
	 * 대댓글 등록
	 * @param rcomment
	 * @return
	 */
	public int registerReComment(Comment rcomment);

	/**
	 * 대댓글 출력
	 * @param commentNo
	 * @return
	 */
	public List<Comment> reCommentShow(Integer commentNo);

	/**
	 * 대댓글 삭제
	 * @param commentNo
	 * @return
	 */
	public int reCommentDelete(Integer commentNo);

}
