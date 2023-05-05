package com.kh.baribari.community.scrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.community.domain.Scrap;
import com.kh.baribari.community.scrap.service.scrapService;


@Controller
public class scrapController {
	
	@Autowired	
	private scrapService sService;
	
	// 스크랩 등록, 삭제, 확인
	@ResponseBody
	@GetMapping("scrapClick")
	public int scrapRegister(
			@RequestParam(value="userNo",required = false) Integer userNo
			,@RequestParam(value="communityNo",required = false) Integer communityNo
			,@RequestParam(value="check",required = false) Integer check
			)throws Exception {
		Scrap scrap = new Scrap(); // 정보를 저장할 스크랩 생성
		scrap.setCommunityNo(communityNo);
		scrap.setUserNo(userNo);
		
		int Serch = scrapSerch(scrap);
		
		if(check > 0) {// 첫 진입이 아니면 실행함.
			if(Serch == 1) {
				sService.scrapDelete(scrap);
			}else if(Serch == 0) {
				sService.scrapRegister(scrap);
			}
		}
		return Serch;
		
	}
	
	// 스크랩이 등록되어 있는지 확인합니다.
	// 0이면 등록 1이면 삭제 해야함.
	public int scrapSerch(Scrap scrap) {
		return sService.scrapSerch(scrap);
	}
	
}
