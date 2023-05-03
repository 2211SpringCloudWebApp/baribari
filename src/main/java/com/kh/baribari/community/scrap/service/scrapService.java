package com.kh.baribari.community.scrap.service;

import com.kh.baribari.community.domain.Scrap;

public interface scrapService {

	/**
	 * 스크랩 등록
	 * @param scrap
	 */
	void scrapRegister(Scrap scrap);

	/**
	 * 스크랩 삭제
	 * @param scrap
	 */
	void scrapDelete(Scrap scrap);

	/**
	 * 스크랩 등록 확인
	 * @param scrap
	 * @return
	 */
	int scrapSerch(Scrap scrap);

}
