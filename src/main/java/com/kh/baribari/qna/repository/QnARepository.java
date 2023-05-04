package com.kh.baribari.qna.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.qna.domain.QnA;

public interface QnARepository {

	/**
	 * qna 등록
	 * @param session
	 * @param qna
	 * @return
	 */
	public int registerQnA(SqlSession session, QnA qna);

	/**
	 * qna 목록
	 * @param session
	 * @param qnaNo
	 * @return
	 */
	public List<QnA> getQnAList(SqlSession session, Integer productNo);

	/**
	 * qna 상세
	 * @param session
	 * @param productNo
	 * @return
	 */
	public int selectByproductNo(SqlSession session, int productNo);

	/**
	 * qna 삭제
	 * @param session
	 * @param productNo
	 * @return
	 */
	public int deleteQnA(SqlSession session, int productNo);

}
