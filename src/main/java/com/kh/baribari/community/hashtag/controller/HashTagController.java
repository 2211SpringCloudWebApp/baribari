package com.kh.baribari.community.hashtag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.community.domain.HashTag;
import com.kh.baribari.community.hashtag.service.HashTagService;

@Controller
public class HashTagController {
	
	@Autowired
	private HashTagService hService;
	
	//해시태그 출력
	@ResponseBody
	@GetMapping("getHashTag")
	public List<HashTag> getHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo) 
			 {
		try {
			System.out.println("해시태그 출력 접근");
			List<HashTag> hList = hService.getHashTag(boardNo);
			return hList;
		} catch (Exception e) {
			return null;
		}
	}
	//해시태그 등록(register) or 삭제(delete)
	@ResponseBody
	@GetMapping("registerHashTag")
	public String registerHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo
			, @RequestParam(value = "hasgTag", required = false) String hasgTag
			, @RequestParam(value = "choice", required = false) String choice
			) {
		try {
			HashTag hTag = new HashTag();
			hTag.setCommunityNo(boardNo);
			hTag.setHashTagName(hasgTag);
			int result;
			if(choice.equals("register")) {
				result = hService.registerHashTag(hTag);
				return "1";
			}else if(choice.equals("delete")){
				result = hService.deleteHashTag(hTag);
				return "0";
			}else {
				return "0";
			}
			
		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
