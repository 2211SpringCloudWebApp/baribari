package com.kh.baribari.community.scrap.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.community.domain.Scrap;

public interface scrapRepository {

	void scrapRegister(SqlSession session, Scrap scrap);

	void scrapDelete(SqlSession session, Scrap scrap);

	int scrapSerch(SqlSession session, Scrap scrap);

}
