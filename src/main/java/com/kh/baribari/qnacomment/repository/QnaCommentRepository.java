package com.kh.baribari.qnacomment.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.qna.domain.QnA;

public interface QnaCommentRepository {

	/**
	 * qna 댓글 등록
	 * @param session
	 * @param qcomment
	 * @return
	 */
	public int qcommentRegister(SqlSession session, QnA qcomment);

	/**
	 * qna 댓글 삭제
	 * @param session
	 * @param commentNo
	 * @return
	 */
	public int qcommentDelete(SqlSession session, Integer commentNo);

	public List<QnA> getOnAList(SqlSession session, Integer productNo);

	public List<QnA> commentShow(SqlSession session, Integer qnaNo);

}
