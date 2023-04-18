package com.kh.baribari.community.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.repository.BoardRepository;
import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.HashTag;

@Service
public class BoardServiceLogic implements BoardService {
	
	@Autowired
	private  BoardRepository brepository;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int getBoardCount() {
		return brepository.getBoardCount(session);
	}

	@Override
	public List<Community> getBoardListAll(PageInfo pi, int category) {
		return brepository.getBoardListAll(session, pi, category);
	}

	@Override
	public int getListCount(int boardNo) {
		return brepository.getListCount(session,boardNo);
	}

	@Override
	public List<HashTag> getHashTag(int boardNo) {
		return brepository.getHashTag(session, boardNo);
	}

	@Override
	public int registerHashTag(HashTag hTag) {
		return brepository.registerHashTag(session, hTag);
	}

	@Override
	public int getSEQ() {
		return brepository.getSEQ(session);
	}

	@Override
	public int boardRegister(Community commu) {
		return brepository.boardRegister(session, commu);
	}
}
