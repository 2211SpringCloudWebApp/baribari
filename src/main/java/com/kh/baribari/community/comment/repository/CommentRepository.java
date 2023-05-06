package com.kh.baribari.community.comment.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.community.domain.Comment;

public interface CommentRepository {

	/**
	 * 댓글 등록
	 * @param session
	 * @param comment
	 * @return
	 */
	public int commentRegister(SqlSession session, Comment comment);

	/**
	 * 댓글 출력
	 * @param session
	 * @param communityNo
	 * @return
	 */
	public List<Comment> commentShow(SqlSession session, Integer communityNo);

	/**
	 * 댓글 삭제
	 * @param session
	 * @param commentNo
	 * @return
	 */
	public int commentDelete(SqlSession session, Integer commentNo);

	/**
	 * 대댓글 등록
	 * @param session
	 * @param rcomment
	 * @return
	 */
	public int registerReComment(SqlSession session, Comment rcomment);

	/**
	 * 대댓글 출력
	 * @param session
	 * @param commentNo
	 * @return
	 */
	public List<Comment> reCommentShow(SqlSession session, Integer commentNo);

	/**
	 * 대댓글 삭제
	 * @param session
	 * @param commentNo
	 * @return
	 */
	public int reCommentDelete(SqlSession session, Integer commentNo);

}
