package com.kh.baribari.qnacomment.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qnacomment.repository.QnaCommentRepository;

@Repository
public class QnaCommentRepositoryLogic implements QnaCommentRepository{

	@Override
	public int qcommentRegister(SqlSession session, QnA qcomment) {
		return session.insert("QnACommentMapper.register", qcomment);
	}

	@Override
	public int qcommentDelete(SqlSession session, Integer commentNo) {
		return session.delete("QnACommentMapper.qelete", commentNo);
	}

	@Override
	public List<QnA> getOnAList(SqlSession session, Integer productNo) {
		return session.selectList("QnACommentMapper.qcommentSelect", productNo);
	}

	@Override
	public List<QnA> commentShow(SqlSession session, Integer qnaNo) {
		return session.selectList("QnACommentMapper.commentShow", qnaNo);
	}

}
