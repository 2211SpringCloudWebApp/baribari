package com.kh.baribari.community.board.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.repository.BoardRepository;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.CommunityPIC;
import com.kh.baribari.community.domain.HashTag;

@Repository
public class BoardRepositoryLogic implements BoardRepository {

	@Override
	public int getBoardCount(SqlSession session, Community comm) {
		return session.selectOne("CommunityMapper.getBoardCount", comm);
	}

	@Override
	public List<Community> getBoardListAll(SqlSession session, PageInfo pi, Community comm) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("CommunityMapper.getBoardListAll",comm , rowBounds);
	}
	
	@Override
	public List<Community> selectHashTagList(SqlSession session, PageInfo pi, String hashTagName) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("CommunityMapper.selectHashTagList",hashTagName, rowBounds);
	}

	@Override
	public int getListCount(SqlSession session, int boardNo) {
		return session.selectOne("CommunityMapper.getListCount", boardNo);
	}

	@Override
	public int getSEQ(SqlSession session) {
		return session.selectOne("CommunityMapper.getSEQ");
	}

	@Override
	public int boardRegister(SqlSession session, Community commu) {
		return session.insert("CommunityMapper.boardRegister", commu);
	}

	@Override
	public int registerPhoto(SqlSession session, CommunityPIC pic) {
		return session.insert("CommunityMapper.registerPhoto",pic);
	}

	@Override
	public int boardDelete(SqlSession session, int boardNo) {
		return session.delete("CommunityMapper.boardDelete",boardNo);
	}

	@Override
	public int deletePhoto(SqlSession session, int boardNo) {
		return session.delete("CommunityMapper.deletePhoto", boardNo);
	}

	@Override
	public int boardUpdate(SqlSession session, Community commu) {
		return session.update("CommunityMapper.boardUpdate",commu);
	}

	@Override
	public Community getBoardOne(SqlSession session, int boardNo) {
		return session.selectOne("CommunityMapper.getBoardOne",boardNo);
	}

	@Override
	public CommunityPIC getPhoto(SqlSession session, Integer boardNo) {
		return session.selectOne("CommunityMapper.getPhoto",boardNo);
	}

	@Override
	public int userPointUp(SqlSession session, int userNo) {
		return session.update("CommunityMapper.userPointUp", userNo);
	}

	@Override
	public void plusViewCount(SqlSession session, Integer boardNo) {
		session.update("CommunityMapper.plusViewCount",boardNo);
	}

	@Override
	public int selectHashTagCount(SqlSession session, String hashTag) {
		return session.selectOne("CommunityMapper.selectHashTagCount", hashTag);
	}

}
