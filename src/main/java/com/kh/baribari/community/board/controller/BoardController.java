package com.kh.baribari.community.board.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.CommunityPIC;
import com.kh.baribari.community.domain.HashTag;
import com.kh.baribari.community.domain.Like;
import com.kh.baribari.community.like.service.LikeService;

@Controller
public class BoardController {

	@Autowired	
	private BoardService bService;
	
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileUpload;
	
	//자유게시판 목록 출력
	@GetMapping("boardList")
	public ModelAndView getBoardList(
			ModelAndView mv
			,@RequestParam(value = "category", required = false, defaultValue = "9") Integer category
			,@RequestParam(value = "sort", required = false, defaultValue = "9") Integer sort
			,@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			,@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword
			,@RequestParam(value = "check", required = false, defaultValue = "9") Integer check
			,@RequestParam(value="hashTagName",required = false, defaultValue = "없") String hashTagName
			) {
		try {
			Community comm = new Community();
			comm.setCommunityCategory(category);
			comm.setSort(sort);	
			comm.setCheck(check);
			
			if(check != 9) {//check값이 있으면 검색어 추가해줌.
				comm.setKeyword(keyword);
			}
			
			//------------------------------------------------------------
			String hashTag = "#" + hashTagName;
			PageInfo pi;
			int bCount;
			
			List<Community> bList;
			if(hashTagName.equals("없")) { // 태그를 클릭한 경우 실행
				bCount = bService.getBoardCount(comm); // 게시글 총 갯수
				pi = new PageInfo(currentPage, bCount, 10); // 페이지 정보 불러오기
				bList = bService.getBoardListAll(pi, comm); // 전체 목록 조회
			}else {
				bCount = bService.selectHashTagCount(hashTag); // 게시글 총 갯수
				pi = new PageInfo(currentPage, bCount, 10); // 페이지 정보 불러오기
				bList = bService.selectHashTagList(pi, hashTag); // 전체 목록 조회
			}
			//------------------------------------------------------------
			
			
			for (Community commu : bList) { //날짜 변경, 좋아요 가져오는 부분
				String data = commu.getCommunityDate();
				LocalDateTime dateTime = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
				String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yy/MM/dd"));
				commu.setCommunityDate(formattedDateTime);
			}
			
			if(bList != null) {
				mv.addObject("bList", bList);
				mv.addObject("bCount", bCount);
				mv.addObject("pi",pi);
				mv.addObject("category",category);
				mv.addObject("sort",sort);
				mv.addObject("hashTagName",hashTagName);
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
	
	//게시글 등록 접속
	@GetMapping("boardRegister")
	public ModelAndView showRegister(ModelAndView mv) {
		try {
			int seq = bService.getSEQ(); // 시퀀스 넘거를 받아옴
			mv.addObject("seq", seq);
			mv.setViewName("community/board/register");
			return mv;
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}
	
	//게시글 등록 완료
	@PostMapping("boardRegister")
	public String boardRegister(
			@RequestParam(value = "subject", required = false) String subject
			,@RequestParam(value = "content", required = false) String content
			,@RequestParam(value = "category", required = false, defaultValue = "9") Integer category
			,@RequestParam(value = "mapX", required = false, defaultValue = "0") String mapX
			,@RequestParam(value = "mapY", required = false, defaultValue = "0") String mapY
			,@RequestParam(value = "userNo", required = false) Integer userNo
			,@RequestParam(value = "seq", required = false) Integer seq
			, HttpServletRequest request) {
		try {
			
			Community commu = new Community();		// 게시글 정보를 담은 변수 생성
			commu.setCommunityNo(seq);				// 시퀀스넘버
			commu.setCommunitySubject(subject);		// 제목
			commu.setCommunityContent(content);		// 내용
			commu.setCommunityCategory(category);	// 말머리
			commu.setMapX(Double.parseDouble(mapX));					// 지도API X좌표
			commu.setMapY(Double.parseDouble(mapY));					// 지도API Y좌표
			commu.setUserNo(userNo);				// 작성자 No

			int result = bService.boardRegister(commu);
			if(result > 0) {
				bService.userPointUp(userNo);
				return "redirect:/boardList";
			}else {
				return "게시글 등록 실패";
			}
			
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	//게시글 상세
	@GetMapping("boardDetail")
	public ModelAndView boardDetail(
			ModelAndView mv
			,@RequestParam(value="communityNo",required = false) Integer boardNo
			,@RequestParam(value="page",required = false) Integer page
			,@RequestParam(value="category",required = false) Integer category
			,@RequestParam(value="sort",required = false) Integer sort
			,@RequestParam(value="hashTagName",required = false) String hashTagName
			
			) throws Exception{
		Community commu = bService.getBoardOne(boardNo);	// 게시글 불러오기
		CommunityPIC pic = bService.getPhoto(boardNo);		// 이미지 불러오기
		
		if(pic != null) {	// 이미지가 존재하면 mv에 추가해줌
			mv.addObject("pic", pic);
		}
		bService.plusViewCount(boardNo); //조회수 증가
		mv.addObject("commu", commu);
		mv.addObject("page", page);
		mv.addObject("category", category);
		mv.addObject("sort", sort);
		mv.addObject("hashTagName", hashTagName);
		mv.setViewName("community/board/detail");
		
		return mv;
	}
	
	//게시글 수정 보여주기
	@GetMapping("boardModify")
	public ModelAndView showModify(
			ModelAndView mv
			,@RequestParam(value="communityNo",required = false) Integer boardNo
			,@RequestParam(value="page",required = false) Integer page
			) throws Exception{
		Community commu = bService.getBoardOne(boardNo);	// 게시글 불러오기
		CommunityPIC pic = bService.getPhoto(boardNo);		// 이미지 불러오기
		if(pic != null) {	// 이미지가 존재하면 mv에 추가해줌
			mv.addObject("pic", pic);
		}
		mv.addObject("commu", commu);
		mv.addObject("page", page);
		mv.setViewName("community/board/modify");
		
		return mv;
	}
	
	//게시글 수정 완료
	@PostMapping("boardModify")
	public String boardModify(
			@RequestParam(value = "subject", required = false) String subject
			,@RequestParam(value = "content", required = false) String content
			,@RequestParam(value = "category", required = false, defaultValue = "9") Integer category
			,@RequestParam(value = "mapX", required = false, defaultValue = "0") String mapX
			,@RequestParam(value = "mapY", required = false, defaultValue = "0") String mapY
			,@RequestParam(value = "userNo", required = false) Integer userNo
			,@RequestParam(value = "seq", required = false) Integer seq
			, HttpServletRequest request
			) {
		
		Community commu = new Community();		// 게시글 정보를 담은 변수 생성
		
		commu.setCommunityNo(seq);				// 시퀀스넘버
		commu.setCommunitySubject(subject);		// 제목
		commu.setCommunityContent(content);		// 내용
		commu.setCommunityCategory(category);	// 말머리
		commu.setMapX(Double.parseDouble(mapX));					// 지도API X좌표
		commu.setMapY(Double.parseDouble(mapY));					// 지도API Y좌표
		commu.setUserNo(userNo);

		bService.boardModify(commu);
		return "redirect:/boardDetail?communityNo=" + seq;
	}
	
	//게시글 삭제
	@PostMapping("boardDelete")
	public String boardDelete(
			@RequestParam(value="communityNo",required = false) Integer boardNo
			)throws Exception {
		int result = bService.boardDelete(boardNo);
		if(result > 0) {
			return "redirect:/boardList";
		}else {
			return "redirect:/boardList";
		}
	}
	
	//에러페이지 가자
	@GetMapping("error")
	public String error() {
		return "error"; // 에러 페이지의 뷰 이름
	}
}
