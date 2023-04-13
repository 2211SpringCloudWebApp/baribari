package com.kh.baribari.community.board.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.repository.BoardRepository;
import com.kh.baribari.community.domain.Community;

@Repository
public class BoardRepositoryLogic implements BoardRepository {

	@Override
	public int getBoardCount(SqlSession session) {
		return session.selectOne("CommunityMapper.getBoardCount");
	}

	@Override
	public List<Community> getBoardListAll(SqlSession session, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("CommunityMapper.getBoardListAll", rowBounds);
	}

}
