package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Notice;
import com.kh.baribari.product.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService nService;
	
	// 게시판 목록
	@GetMapping("/noticeList")
	public ModelAndView noticeList(ModelAndView mv
			, @RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
		try {
			int totalCount = nService.noticeListCount();
			PageInfo pi = new PageInfo(page, totalCount, 10);
			List<Notice> nList = nService.selectNoticeList(pi);
//			if(nList != null) {
				mv.addObject("nList", nList);
				mv.addObject("pi", pi);
				mv.setViewName("shopping/noticelist");
				return mv;
//			}else {
//				mv.addObject("msg", "오류").setViewName("error");
//				return mv;
//			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}
}
