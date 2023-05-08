package com.kh.baribari.qnacomment.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.qnacomment.domain.QnaComment;
import com.kh.baribari.qnacomment.repository.QnaCommentRepository;
import com.kh.baribari.qnacomment.service.QnaCommentService;
@Service
public class QnaCommentImpl implements QnaCommentService{
	@Autowired
	private QnaCommentRepository qcRespository;
	@Autowired
	private SqlSession session;

	@Override
	public int qcommentRegister(QnaComment qcomment) {
		int result = qcRespository.qcommentRegister(session, qcomment);
		return result;
	}

	@Override
	public int deleteQComment(Integer commentNo) {
		int result = qcRespository.qcommentDelete(session, commentNo);
		return result;
	}

}
