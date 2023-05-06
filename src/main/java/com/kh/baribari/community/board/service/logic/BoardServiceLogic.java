package com.kh.baribari.community.board.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.repository.BoardRepository;
import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.CommunityPIC;
import com.kh.baribari.community.domain.HashTag;

@Service
public class BoardServiceLogic implements BoardService {
	
	@Autowired
	private  BoardRepository brepository;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int getBoardCount(Community comm) {
		return brepository.getBoardCount(session, comm);
	}

	@Override
	public List<Community> getBoardListAll(PageInfo pi, Community comm) {
		return brepository.getBoardListAll(session, pi, comm);
	}
	
	@Override
	public List<Community> selectHashTagList(PageInfo pi, String hashTagName) {
		return brepository.selectHashTagList(session, pi, hashTagName);
	}

	@Override
	public int getListCount(int boardNo) {
		return brepository.getListCount(session,boardNo);
	}

	@Override
	public int getSEQ() {
		return brepository.getSEQ(session);
	}

	@Override
	public int boardRegister(Community commu) {
		return brepository.boardRegister(session, commu);
	}

	@Override
	public int registerPhoto(CommunityPIC pic) {
		return brepository.registerPhoto(session, pic);
	}

	@Override
	public int boardDelete(int boardNo) {
		return brepository.boardDelete(session, boardNo);
	}

	@Override
	public int deletePhoto(int boardNo) {
		return brepository.deletePhoto(session, boardNo);
	}

	@Override
	public int boardUpdate(Community commu) {
		return brepository.boardUpdate(session, commu);
	}

	@Override
	public Community getBoardOne(int boardNo) {
		return brepository.getBoardOne(session, boardNo);
	}

	@Override
	public CommunityPIC getPhoto(Integer boardNo) {
		return brepository.getPhoto(session, boardNo);
	}

	@Override
	public int userPointUp(int userNo) {
		return brepository.userPointUp(session, userNo);
	}

	@Override
	public void plusViewCount(Integer boardNo) {
		brepository.plusViewCount(session, boardNo);
	}

	@Override
	public int selectHashTagCount(String hashTag) {
		return brepository.selectHashTagCount(session, hashTag);
	}

	

}
