package com.kh.baribari.community.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.baribari.community.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired	
	private BoardService bService;
	
	//자유게시판 목록 출력
	@GetMapping("board/list")
	public String getBoardList() {
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
