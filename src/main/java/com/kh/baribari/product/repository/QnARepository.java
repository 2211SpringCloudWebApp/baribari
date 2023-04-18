package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.product.domain.QnA;

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

}
