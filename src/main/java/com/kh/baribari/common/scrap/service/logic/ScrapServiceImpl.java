package com.kh.baribari.common.scrap.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.scrap.domain.Scrap;
import com.kh.baribari.common.scrap.repository.ScrapRepository;
import com.kh.baribari.common.scrap.service.ScrapService;

@Service
public class ScrapServiceImpl implements ScrapService {
	@Autowired
	private SqlSession session;
	@Autowired
	private ScrapRepository sRepository;

	@Override
	public int addScrap(Scrap scrap) {
		int result = sRepository.addScrap(session, scrap);
		return result;
	}
}
