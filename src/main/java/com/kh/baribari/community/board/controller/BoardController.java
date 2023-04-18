package com.kh.baribari.community.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.HashTag;

import org.springframework.ui.Model;

@Controller
public class BoardController {

	@Autowired	
	private BoardService bService;

	//자유게시판 목록 출력
	@GetMapping("boardList")
	public ModelAndView getBoardList(
			ModelAndView mv
			,@RequestParam(value = "category", required = false, defaultValue = "9") Integer category
			,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try {
			// 게시글 총 갯수
			int bCount = bService.getBoardCount();
			System.out.println(category);
			System.out.println(currentPage);
			// 페이지 정보 불러오기
			PageInfo pi = new PageInfo(currentPage, bCount, 10);
			List<Community> bList = bService.getBoardListAll(pi, category); // 전체 목록 조회
			
			if(bList != null) {
				mv.addObject("bList", bList);
				mv.addObject("bCount", bCount);
				mv.addObject("pi",pi);
				mv.setViewName("community/board/list");
				return mv;
			} else {
				mv.addObject("msg", "게시글 목록을 가져오지 못했습니다.").setViewName("error");
				return mv;
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}
	
	//게시글 등록
	@GetMapping("boardRegister")
	public String boardRegister() {
		
		return "community/board/register";
	}
	
	//해시태그 출력
	@ResponseBody
	@GetMapping("boardgetHashTag")
	public String getHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo) 
			 {
			
		try {
			List<HashTag> hList = bService.getHashTag(boardNo);
			System.out.println(hList);
			if(hList != null) {
				return "1";
			}else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	//해시태그 등록
	@ResponseBody
	@GetMapping("boardregisterHashTag")
	public String registerHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo
			, @RequestParam(value = "hasgTag", required = false) String hashTag
			) {
		try {
			HashTag hTag = null;
			hTag.setCommunityNo(boardNo);
			hTag.setHashTag(hashTag);
			
			int result = bService.registerHashTag(hTag);
			if(result > 0) {
				return "1";
			}else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//게시글 상세
	@GetMapping("boardDetail")
	public String boardDetail() {
		return "community/board/detail";
	}
	
	//게시글 수정
	@GetMapping("boardModify")
	public String boardModify() {
		return "community/board/modify";
	}
	
	//게시글 삭제
	@GetMapping("boardDelete")
	public String boardDelete() {
		return "";
	}
	
	//에러페이지 가자
	@GetMapping("error")
    public String error() {
        return "error"; // 에러 페이지의 뷰 이름
    }
	
	// 좋아요 갯수 조회
	public int getListCount(int boardNo) {
		return bService.getListCount(boardNo);
	}
	
}
