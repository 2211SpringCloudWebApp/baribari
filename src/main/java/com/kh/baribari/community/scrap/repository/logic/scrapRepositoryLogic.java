package com.kh.baribari.community.scrap.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.domain.Scrap;
import com.kh.baribari.community.scrap.repository.scrapRepository;

@Repository
public class scrapRepositoryLogic implements scrapRepository{

	@Override
	public void scrapRegister(SqlSession session, Scrap scrap) {
		session.insert("ScrapMapper.scrapRegister", scrap);
	}

	@Override
	public void scrapDelete(SqlSession session, Scrap scrap) {
		session.delete("ScrapMapper.scrapDelete", scrap);
	}

	@Override
	public int scrapSerch(SqlSession session, Scrap scrap) {
		return session.selectOne("ScrapMapper.scrapSerch", scrap);
	}

	
}
