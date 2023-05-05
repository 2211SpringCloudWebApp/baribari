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

}
