package com.kh.baribari.community.board.repository;

import org.apache.ibatis.session.SqlSession;

public interface BoardRepository {

	public int getBoardCount(SqlSession session);

}
