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
		/*
	//게시글 등록
	@GetMapping("boardRegister")
	public String boardRegister(
			@RequestParam(value = "subject", required = false) String subject
			,@RequestParam(value = "content", required = false) String content
			,@RequestParam(value = "category", required = false) Integer category
			,@RequestParam(value = "mapX", required = false) Integer mapX
			,@RequestParam(value = "mapY", required = false) Integer mapY
			,@RequestParam(value = "userNo", required = false) Integer userNo
			) {
		try {
			int seq = bService.getSEQ(); // 시퀀스 넘거를 받아옴
			
			Community commu = new Community();		// 정보를 담은 해시태그 생성
			commu.setCommunityNo(seq);				// 시퀀스넘버
			commu.setCommunitySubject(subject);		// 제목
			commu.setCommunityContent(content);		// 내용
			commu.setCommunityCategory(category);	// 말머리
			commu.setMapX(mapX);					// 지도API X좌표
			commu.setMapY(mapY);					// 지도API Y좌표
			commu.setUserNo(userNo);				// 작성자 No
			
			int result = bService.boardRegister(commu);
			
			if(result > 0) {
				return "community/board/register";
			}else {
				return "0";
			}
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	*/
	//해시태그 출력
	@ResponseBody
	@GetMapping("getHashTag")
	public String getHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo) 
			 {
			
		try {
			List<HashTag> hList = bService.getHashTag(boardNo);
			System.out.println("hList : " + hList.get(0).getHashTag());
			String hashTagResult = "";
			for(int i = 0; i < hList.size(); i++) {
				hashTagResult += hList.get(i).getHashTag();
				if(i < hList.size() - 1) {
					hashTagResult += " ";
				}
			}
			
			System.out.println(hashTagResult);
			if(hList != null) {
				return hashTagResult;
			}else {
				return "0";
			}
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	//해시태그 등록
	@ResponseBody
	@GetMapping("registerHashTag")
	public String registerHashTag(
			@RequestParam(value="communityNo",required = false) Integer boardNo
			, @RequestParam(value = "hasgTag", required = false) String hasgTag
			) {
		try {
			HashTag hTag = new HashTag();
			hTag.setCommunityNo(boardNo);
			hTag.setHashTag(hasgTag);
			System.out.println("해시태그 등록 접근");
			System.out.println(hTag);
			int result = bService.registerHashTag(hTag);
			System.out.println(result);
			if(result > 0) {
				System.out.println("성공");
				return "1";
			}else {
				System.out.println("실패");
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
