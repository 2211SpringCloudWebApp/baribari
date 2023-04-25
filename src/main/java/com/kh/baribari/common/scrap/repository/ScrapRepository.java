package com.kh.baribari.common.scrap.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.scrap.domain.Scrap;

public interface ScrapRepository {
	/**
	 * 찜하기
	 * @param SqlSession, Scrap
	 * @return int
	 */
	public int addScrap(SqlSession session, Scrap scrap);

}
