package com.kh.baribari.community.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String getBoardList(
			Model model
			,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try {
			// 게시글 총 갯수
			int bCount = bService.getBoardCount();
			PageInfo pi = new PageInfo(currentPage, bCount, 10);
			List<Community> bList = bService.getBoardListAll(pi);
	
		} catch (Exception e) {
		}
		return "community/board/list";
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
	
}
