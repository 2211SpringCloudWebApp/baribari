package com.kh.baribari.common.scrap.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.scrap.domain.Scrap;
import com.kh.baribari.common.scrap.repository.ScrapRepository;

@Repository
public class ScrapRepositoryLogic implements ScrapRepository {
	@Override
	public int addScrap(SqlSession session, Scrap scrap) {
		int result = session.insert("ScrapMapper.addScrap", scrap);
		return result;
	}

}
