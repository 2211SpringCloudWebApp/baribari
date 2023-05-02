package com.kh.baribari.common.scrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.scrap.domain.Scrap;
import com.kh.baribari.common.scrap.service.ScrapService;

@Controller
public class ScrapController {
	@Autowired
	private ScrapService sService;
	
	// 찜하기: communityNo가 99999로 시작
	@ResponseBody
	public int addScrap(ModelAndView mv, int productNo, int userNo) {
		System.out.println(productNo + ", " + userNo);
		Scrap scrap = new Scrap();
		// SCRAP 테이블에 구분을 위해 productNo 변경
		scrap.setCommunityNo(Integer.parseInt("99999" + String.valueOf(productNo)));
		scrap.setUserNo(userNo);
		System.out.println(scrap);
		int result = sService.addScrap(scrap);
		return result;
	}
}
