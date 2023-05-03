package com.kh.baribari.community.scrap.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.domain.Scrap;
import com.kh.baribari.community.scrap.repository.scrapRepository;
import com.kh.baribari.community.scrap.service.scrapService;

@Service
public class scrapServiceLogic implements  scrapService{

	@Autowired
	private scrapRepository srepository;
	
	@Autowired
	private SqlSession session;

	@Override
	public void scrapRegister(Scrap scrap) {
		srepository.scrapRegister(session, scrap);
	}

	@Override
	public void scrapDelete(Scrap scrap) {
		srepository.scrapDelete(session, scrap);
	}

	@Override
	public int scrapSerch(Scrap scrap) {
		return srepository.scrapSerch(session, scrap);
	}
	
}
