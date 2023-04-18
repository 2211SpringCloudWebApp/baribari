package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.product.domain.QnA;
import com.kh.baribari.product.repository.QnARepository;

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

}
