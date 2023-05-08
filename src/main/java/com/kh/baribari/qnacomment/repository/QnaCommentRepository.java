package com.kh.baribari.qnacomment.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.qnacomment.domain.QnaComment;

public interface QnaCommentRepository {

	/**
	 * qna 댓글 등록
	 * @param session
	 * @param qcomment
	 * @return
	 */
	public int qcommentRegister(SqlSession session, QnaComment qcomment);

	/**
	 * qna 댓글 삭제
	 * @param session
	 * @param commentNo
	 * @return
	 */
	public int qcommentDelete(SqlSession session, Integer commentNo);

}
