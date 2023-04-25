package com.kh.baribari.common.scrap.service;

import com.kh.baribari.common.scrap.domain.Scrap;

public interface ScrapService {

	/**
	 * 찜하기
	 * @param Scrap
	 * @return int
	 */
	public int addScrap(Scrap scrap);

}
