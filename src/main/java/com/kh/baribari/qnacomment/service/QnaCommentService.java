package com.kh.baribari.qnacomment.service;

import java.util.List;

import com.kh.baribari.qna.domain.QnA;

public interface QnaCommentService {

	/**
	 * qna 댓글 등록
	 * @param qcomment
	 * @return
	 */
	public int qcommentRegister(QnA qcomment);

	/**
	 * qna 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	public int deleteQComment(Integer commentNo);

	public List<QnA> getQnAList(Integer productNo);

}
