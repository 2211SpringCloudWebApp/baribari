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
	public int getBoardCount(SqlSession session) {
		return session.selectOne("CommunityMapper.getBoardCount");
	}

	@Override
	public List<Community> getBoardListAll(SqlSession session, PageInfo pi, int category) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("CommunityMapper.getBoardListAll",category , rowBounds);
	}

	@Override
	public int getListCount(SqlSession session, int boardNo) {
		return session.selectOne("CommunityMapper.getListCount", boardNo);
	}

	@Override
	public List<HashTag> getHashTag(SqlSession session,int boardNo) {
		return session.selectList("CommunityMapper.getHashTag", boardNo);
	}

	@Override
	public int registerHashTag(SqlSession session, HashTag hTag) {
		return session.insert("CommunityMapper.registerHashTag", hTag);
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
	public int deleteHashTag(SqlSession session, HashTag hTag) {
		return session.delete("CommunityMapper.deleteHashTag",hTag);
	}

	@Override
	public int registerPhoto(SqlSession session, CommunityPIC pic) {
		System.out.println(pic);
		return session.insert("CommunityMapper.registerPhoto",pic);
	}

}
