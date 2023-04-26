package com.kh.baribari.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	// 게시판 글쓰기 화면
	@GetMapping("/write")
	public ModelAndView noticeWriteView(ModelAndView mv) {
		mv.setViewName("notice/noticeWrite");
		return mv;
	}

	// 게시판 글쓰기 등록
	@PostMapping("/write")
	public ModelAndView noticeWrite(ModelAndView mv, Notice notice) {
		try {
			int result = nService.writeNotice(notice);
			if (result > 0) {
				mv.addObject("msg", "글쓰기가 완료되었습니다."); // 성공 메시지 설정
				mv.setViewName("redirect:/shopping/noticeList"); // 글쓰기 완료 후 게시판 목록 페이지로 리다이렉트
			} else {
				mv.addObject("msg", "글쓰기에 실패하였습니다. 다시 시도해주세요."); // 실패 메시지 설정
				mv.setViewName("/shopping/noticeWrite"); // 글쓰기 페이지로 이동
			}
		} catch (Exception e) {
			mv.addObject("msg", "글쓰기 중 오류가 발생하였습니다. 다시 시도해주세요."); // 오류 메시지 설정
			mv.setViewName("/shopping/noticeWrite"); // 글쓰기 페이지로 이동
			e.printStackTrace();
		}
		return mv;
	}

	// 게시판 수정 view
	@GetMapping("/modify")
	public ModelAndView noticeModifyView(@RequestParam("noticeNo") int noticeNo, ModelAndView mv) {
		try {
			Notice notice = nService.noticeModifyView(noticeNo);
			if (notice != null) {
				mv.addObject("notice", notice);
				mv.setViewName("/shopping/noticeModify");
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
				mv.addObject("msg", "게시글이 수정되었습니다.");
				mv.setViewName("redirect:/shopping/noticeDetail?noticeNo=" + notice.getNoticeNo());
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
	@PostMapping("/delete")
	public ModelAndView noticeDelete(@RequestParam("noticeNo") int noticeNo) {
		ModelAndView mv = new ModelAndView();
		try {
			int result = nService.deleteNotice(noticeNo);
			if (result > 0) {
				mv.addObject("msg", "게시글이 삭제되었습니다.");
				mv.setViewName("redirect:/shopping/noticeList");
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
				Notice notice = nService.selectOneByNo(noticeNo);
				int viewCount = nService.updateViewCount(noticeNo);
				String userId = notice.getUserId();
				User writer = uService.selectUserByuserId(userId);
				if (userId != null && !userId.equals("")) {
					mv.addObject("notice", notice);
					mv.addObject("viewCount", viewCount);
					mv.addObject("userId", userId);
					mv.addObject("writer", writer);
					mv.setViewName("notice/noticeDetail");
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
				mv.setViewName("shopping/noticesearch");
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
