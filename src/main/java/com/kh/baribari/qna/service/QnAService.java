package com.kh.baribari.qna.service;

import java.util.List;

import com.kh.baribari.qna.domain.QnA;

public interface QnAService {

	/**
	 * qna 등록
	 * @param qna
	 * @return
	 */
	public int registerQnA(QnA qna);

	/**
	 * qna 목록
	 * @param qnaNo
	 * @return
	 */
	public List<QnA> getQnAList(Integer productNo);

}
