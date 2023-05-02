package com.kh.baribari.community.like.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.community.domain.Like;
import com.kh.baribari.community.like.service.LikeService;

@Controller
public class LikeController {
	
	@Autowired
	private LikeService lService;
	
	@ResponseBody
	@GetMapping("clickLike")
	public int clickLike(
			@RequestParam(value="communityNo",required = false) Integer boardNo 
			,@RequestParam(value="userNo",required = false) Integer userNo
			) throws Exception{
		
		Like like = new Like();
		like.setCommunityNo(boardNo);
		like.setUserNo(userNo);
		
		//좋아요를 누른 상태인지 아닌지 확인
		int testLike = lService.testLike(like);
		if(testLike > 0) {
			// 좋아요 삭제 해야함.
			lService.deleteLike(like);
		} else {
			// 좋아요 등록 해야함.
			lService.registerLike(like);
		}
		// 좋아요 개수 조회
		return lService.showLike(like);
	}
	@ResponseBody
	@GetMapping("showLike")
	public int showLike(
			@RequestParam(value="communityNo",required = false) Integer boardNo 
			,@RequestParam(value="userNo",required = false) Integer userNo
			)throws Exception{
		Like like = new Like();
		like.setCommunityNo(boardNo);
		like.setUserNo(userNo);
		
		return lService.showLike(like);
	}
}
