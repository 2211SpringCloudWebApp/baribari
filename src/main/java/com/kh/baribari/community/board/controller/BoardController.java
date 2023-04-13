package com.kh.baribari.community.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.community.domain.Community;

import org.springframework.ui.Model;

@Controller
public class BoardController {

	@Autowired	
	private BoardService bService;
	
	//자유게시판 목록 출력
	@GetMapping("board/list")
	public ModelAndView getBoardList(
			ModelAndView mv
			,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try {
			// 게시글 총 갯수
			int bCount = bService.getBoardCount();
			// 페이지 정보 불러오기
			PageInfo pi = new PageInfo(currentPage, bCount, 10);
			List<Community> bList = bService.getBoardListAll(pi);
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
	@GetMapping("board/register")
	public String boardRegister() {
		return "community/board/register";
	}
	
	//게시글 상세
	@GetMapping("board/detail")
	public String boardDetail() {
		return "community/board/detail";
	}
	
	//게시글 수정
	@GetMapping("board/modify")
	public String boardModify() {
		return "community/board/modify";
	}
	
	//게시글 삭제
	@GetMapping("board/delete")
	public String boardDelete() {
		return "";
	}
	
	//에러페이지 가자
	@GetMapping("/error")
    public String error() {
        return "error"; // 에러 페이지의 뷰 이름
    }
	
}
