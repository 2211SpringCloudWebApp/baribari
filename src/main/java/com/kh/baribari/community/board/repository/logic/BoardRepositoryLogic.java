package com.kh.baribari.community.board.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.board.repository.BoardRepository;

@Repository
public class BoardRepositoryLogic implements BoardRepository {

	@Override
	public int getBoardCount(SqlSession session) {
		return session.selectOne("CommunityMapper.getBoardCount");
	}

}
