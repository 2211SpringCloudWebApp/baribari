package com.kh.baribari.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.notice.domain.Notice;
import com.kh.baribari.notice.service.NoticeService;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService nService;
	@Autowired
	private UserService uService;
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileInfo;
	// 게시판 글쓰기 화면
	@GetMapping("/write")
	public ModelAndView noticeWriteView(ModelAndView mv) {
		mv.setViewName("notice/noticewrite");
		return mv;
	}

	// 게시판 글쓰기 등록
	@PostMapping("/write")
	public ModelAndView noticeWrite(ModelAndView mv, Notice notice, HttpServletRequest request, @RequestParam(value = "uploadFile", required = false) List<MultipartFile> noticePic) {
		try {
			Map<String, String> fMap = new HashMap<String, String>();
	    	// 파일 경로
	        String path = "notice";
	        int i = 1;
	        // 첨부파일이 있을 경우 파일 저장
	        System.out.println("noticePic : " + noticePic);
	        
	        if (noticePic != null) {
	        	for (MultipartFile file : noticePic) {
	        		Map<String, String> files = fileInfo.saveFile(file, request, path);
	        		
	        		System.out.println("files : " + files);
	        		
	        		for (String k : files.keySet()) {
	        			String key = "file" + i;
	        			String value = files.get(k);
	        			fMap.put(key, value);
	        			if (i == 1) {
	        				notice.setNoticePic(value);
	        			}
	        		}
	        	}
			}
			int result = nService.writeNotice(notice);
			if (result > 0) {
				mv.setViewName("redirect:/notice/list"); // 글쓰기 완료 후 게시판 목록 페이지로 리다이렉트
			} else {
				mv.addObject("msg", "글쓰기에 실패하였습니다. 다시 시도해주세요."); // 실패 메시지 설정
				mv.setViewName("/notice/noticewrite"); // 글쓰기 페이지로 이동
			}
		} catch (Exception e) {
			mv.addObject("msg", "글쓰기 중 오류가 발생하였습니다. 다시 시도해주세요."); // 오류 메시지 설정
			mv.setViewName("/notice/noticewrite"); // 글쓰기 페이지로 이동
			e.printStackTrace();
		}
		return mv;
	}

	// 게시판 수정 view
	@GetMapping("/modify")
	public ModelAndView noticeModifyView(@RequestParam("noticeNo") int noticeNo, ModelAndView mv) {
		try {
			Notice notice = nService.selectOneByNo(noticeNo);
			if (notice != null) {
				mv.addObject("notice", notice);
				mv.addObject("noticeNo", noticeNo);
				mv.setViewName("/notice/noticemodify");
			} else {
				mv.addObject("msg", "데이터 조회에 실패하였습니다.");
				mv.setViewName("/common/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/common/error");
		}
		return mv;
	}

	// 게시판 수정 logic
	@PostMapping("/modify")
	public ModelAndView noticeModify(ModelAndView mv, Notice notice) {
		try {
			int result = nService.modifyNotice(notice);
			if (result > 0) {
				mv.setViewName("redirect:/notice/detail?noticeNo=" + notice.getNoticeNo());
			} else {
				mv.addObject("msg", "게시글 수정에 실패하였습니다.");
				mv.setViewName("/common/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/common/error");
		}
		return mv;
	}

	// 게시판 삭제
	@GetMapping("/delete")
	public ModelAndView noticeDelete(ModelAndView mv, @RequestParam("noticeNo") int noticeNo) {
		try {
			int result = nService.deleteNotice(noticeNo);
			if (result > 0) {
				mv.setViewName("redirect:/notice/list");
			} else {
				mv.addObject("msg", "게시글 삭제에 실패하였습니다.");
				mv.setViewName("/common/error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/common/error");
		}
		return mv;
	}

	// 게시판 목록
	@GetMapping("/list")
	public ModelAndView noticeList(ModelAndView mv,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
		try {
			int totalCount = nService.noticeListCount();
			PageInfo pi = new PageInfo(page, totalCount, 10);
			List<Notice> nList = nService.selectNoticeList(pi);
//			if(nList != null) {
			mv.addObject("nList", nList);
			mv.addObject("pi", pi);
			mv.setViewName("notice/noticelist");
			return mv;
//			}else {
//				mv.addObject("msg", "오류").setViewName("error");
//				return mv;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}

	// 게시판 상세조회
	@GetMapping("/detail")
	public ModelAndView noticeDetail(ModelAndView mv, int noticeNo) {
		try {
			int result = nService.updateViewCount(noticeNo);
			if (result > 0) {
				
				Notice noticePrev = nService.selectOneByNo(noticeNo-1);
				Notice notice = nService.selectOneByNo(noticeNo);
				Notice noticeNext = nService.selectOneByNo(noticeNo+1);
				int viewCount = nService.updateViewCount(noticeNo);
				String userId = notice.getUserId();
//				User writer = uService.selectUserByuserId(userId);
				if (userId != null && !userId.equals("")) {
					mv.addObject("noticePrev", noticePrev);
					mv.addObject("notice", notice);
					mv.addObject("noticeNext", noticeNext);
					mv.addObject("viewCount", viewCount);
					mv.addObject("userId", userId);
//					mv.addObject("writer", writer);
					mv.setViewName("notice/noticedetail");
					return mv;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
		return mv;
	}

	// 게시판 검색
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView noticeSearch(ModelAndView mv, Search search,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		try {
			int totalCount = nService.noticeSearch(search);
			PageInfo pi = new PageInfo(currentPage, totalCount, 10);
			List<Notice> searchList = nService.selectListByKeyword(pi, search);
			if (!searchList.isEmpty()) {
				mv.addObject("searchList", searchList);
				mv.addObject("pi", pi);
				mv.setViewName("notice/noticesearch");
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
		return mv;
	}
}
