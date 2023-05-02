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
			,@RequestParam(value = "keyword", required = false, defaultValue = "키워드") String keyword
			,@RequestParam(value = "check", required = false, defaultValue = "9") Integer check
			) {
		try {
			// 게시글 총 갯수
			int bCount = bService.getBoardCount(category);
			// 페이지 정보 불러오기
			PageInfo pi = new PageInfo(currentPage, bCount, 10);
			Community comm = new Community();
			comm.setCommunityCategory(category);
			comm.setSort(sort);	
			comm.setCheck(check);
			if(check != 9) {//check값이 있으면 검색어 추가해줌.
				comm.setKeyword(keyword);
			}
			
			List<Community> bList = bService.getBoardListAll(pi, comm); // 전체 목록 조회
			
			//날짜 변경, 좋아요 가져오는 부분
			for (Community commu : bList) {
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
			,@RequestParam(value = "fileList", required = false) List<MultipartFile> fList
			, HttpServletRequest request) {
		try {
			
			Community commu = new Community();		// 게시글 정보를 담은 변수 생성
			CommunityPIC pic = new CommunityPIC();	// 사진 정보를 담을 변수 생성
			commu.setCommunityNo(seq);				// 시퀀스넘버
			commu.setCommunitySubject(subject);		// 제목
			commu.setCommunityContent(content);		// 내용
			commu.setCommunityCategory(category);	// 말머리
			commu.setMapX(Double.parseDouble(mapX));					// 지도API X좌표
			commu.setMapY(Double.parseDouble(mapY));					// 지도API Y좌표
			commu.setUserNo(userNo);				// 작성자 No
			Map<String, String> fMap = new HashMap<String, String>();
			// 파일 경로
			String path = "community/board";
			int i = 1;
			// 첨부파일이 있을 경우 파일 저장
			pic.setCommunityNo(seq);
			System.out.println("pic : " + pic.getCommunityNo());
			System.out.println("commu :" + commu.getCommunityNo());
			if (fList != null) {
				for (MultipartFile file : fList) {
					Map<String, String> fileInfo = fileUpload.saveFile(file, request, path);
					for (String k : fileInfo.keySet()) {// fileInfo 맵의 keySet() 메서드로 모든 키를 가져와서 변수 k에 하나씩 저장하는 반복문 시작
						String key = "file" + i; // 문자열 "file"과 i를 합쳐서 변수 key에 저장
						String value = fileInfo.get(k);// fileInfo 맵에서 키 k에 해당하는 값을 가져와서 변수 value에 저장
						System.out.println("value값 출력한다잉 : " + value);
						
						fMap.put(key, value);
						if (i == 1) {
							pic.setCommunityPic1(value);
						} else if (i == 2) {
							pic.setCommunityPic2(value);
						} else if (i == 3) {
							pic.setCommunityPic3(value);
						} else if (i == 4) {
							pic.setCommunityPic4(value);
						} else if (i == 5) {
							pic.setCommunityPic5(value);
						} else if (i == 6) {
							pic.setCommunityPic6(value);
						} else if (i == 7) {
							pic.setCommunityPic7(value);
						} else if (i == 8) {
							pic.setCommunityPic8(value);
						} else if (i == 9) {
							pic.setCommunityPic9(value);
						} else if (i == 10) {
							pic.setCommunityPic10(value);
						}
					}
					i++;
				}
			}
			int result = bService.boardRegister(commu);
			System.out.println(result);
			if(result > 0) {
				bService.userPointUp(userNo);
				bService.registerPhoto(pic);
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
			,@RequestParam(value="communityNo",required = false) Integer boardNo) throws Exception{
		Community commu = bService.getBoardOne(boardNo);	// 게시글 불러오기
		CommunityPIC pic = bService.getPhoto(boardNo);		// 이미지 불러오기
		
		if(pic != null) {	// 이미지가 존재하면 mv에 추가해줌
			mv.addObject("pic", pic);
		}
		bService.plusViewCount(boardNo); //조회수 증가
		mv.addObject("commu", commu);
		mv.setViewName("community/board/detail");
		
		return mv;
	}
	//게시글 수정 보여주기
	@GetMapping("boardModify")
	public ModelAndView showModify(
			ModelAndView mv
			,@RequestParam(value="communityNo",required = false) Integer boardNo) throws Exception{
		Community commu = bService.getBoardOne(boardNo);	// 게시글 불러오기
		CommunityPIC pic = bService.getPhoto(boardNo);		// 이미지 불러오기
		if(pic != null) {	// 이미지가 존재하면 mv에 추가해줌
			mv.addObject("pic", pic);
		}
		mv.addObject("commu", commu);
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
			,@RequestParam(value = "userNo", required = false, defaultValue = "0") Integer userNo
			,@RequestParam(value = "seq", required = false) Integer seq
			,@RequestParam(value = "fileList", required = false) List<MultipartFile> fList
			, HttpServletRequest request
			) {
		
		return "community/board/detail";
	}
	
	//게시글 삭제
	@PostMapping("boardDelete")
	public String boardDelete(
			@RequestParam(value="communityNo",required = false) Integer boardNo
			)throws Exception {
		System.out.println(boardNo);
		int result = bService.boardDelete(boardNo);
		System.out.println(result);
		if(result > 0) {
			System.out.println("삭제성공입니다.");
			return "redirect:/boardList";
		}else {
			System.out.println("삭제실패입니다.");
			return "redirect:/boardList";
		}
	}

	//에러페이지 가자
	@GetMapping("error")
	public String error() {
		return "error"; // 에러 페이지의 뷰 이름
	}
}
