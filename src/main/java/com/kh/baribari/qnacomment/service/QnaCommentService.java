package com.kh.baribari.qnacomment.service;

import com.kh.baribari.qnacomment.domain.QnaComment;

public interface QnaCommentService {

	/**
	 * qna 댓글 등록
	 * @param qcomment
	 * @return
	 */
	public int qcommentRegister(QnaComment qcomment);

	/**
	 * qna 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	public int deleteQComment(Integer commentNo);

}
