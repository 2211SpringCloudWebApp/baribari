package com.kh.baribari.qna.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qna.repository.QnARepository;
import com.kh.baribari.qna.service.QnAService;

@Service
public class QnAServiceImpl implements QnAService {
	
	@Autowired
	private QnARepository qRepository;
	@Autowired
	private SqlSession session;

	@Override
	public int registerQnA(QnA qna) {
		int result = qRepository.registerQnA(session, qna);
		return result;
	}

	@Override
	public List<QnA> getQnAList(Integer productNo) {
		List<QnA> qList = qRepository.getQnAList(session, productNo);
		return qList;
	}

	@Override
	public int selectByproductNo(int productNo) {
		int result = qRepository.selectByproductNo(session, productNo);
		return result;
	}

}
