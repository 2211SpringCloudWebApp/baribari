package com.kh.baribari.qnacomment.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.qnacomment.domain.QnaComment;
import com.kh.baribari.qnacomment.repository.QnaCommentRepository;

@Repository
public class QnaCommentRepositoryLogic implements QnaCommentRepository{

	@Override
	public int qcommentRegister(SqlSession session, QnaComment qcomment) {
		return session.insert("qCommentMapper.qcommentRegister", qcomment);
	}

	@Override
	public int qcommentDelete(SqlSession session, Integer commentNo) {
		return session.delete("qCommentMapper.qcommentDelete", commentNo);
	}
	
	

}
