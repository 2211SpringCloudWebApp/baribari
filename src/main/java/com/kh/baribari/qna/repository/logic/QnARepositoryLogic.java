package com.kh.baribari.qna.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qna.repository.QnARepository;

@Repository
public class QnARepositoryLogic implements QnARepository{

	@Override
	public int registerQnA(SqlSession session, QnA qna) {
		int result = session.insert("QnAMapper.registerQnA", qna);
		return result;
	}

	@Override
	public List<QnA> getQnAList(SqlSession session, Integer productNo) {
		List<QnA> qList = session.selectList("QnAMapper.getQnAList", productNo);
		return qList;
	}

	@Override
	public int selectByproductNo(SqlSession session, int productNo) {
		int result = session.selectOne("QnAMapper.selectByproductNo", productNo);
		return result;
	}

	@Override
	public int deleteQnA(SqlSession session, int productNo) {
		int result = session.delete("QnAMapper.deleteQnA", productNo);
		return result;
	}

}
