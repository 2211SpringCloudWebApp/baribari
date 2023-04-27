package com.kh.baribari.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qna.service.QnAService;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	@Autowired
	private QnAService qService;
	@Autowired
	private UserService uService;

	// QnA 등록
	@PostMapping("/register")
	@ResponseBody
	public String writeQnAView(@ModelAttribute QnA qna, HttpServletRequest request, Model model) {
		try {
			System.out.println(qna);
			int result = qService.registerQnA(qna);
			if (result > 0) {
				return "1";
			} else {
				model.addAttribute("msg", "상품문의 등록이 되지 않았습니다.");
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	// QnA 목록 출력
	@GetMapping("/list")
	@ResponseBody
	public String getQnAList(Integer productNo) {
		List<QnA> qList = qService.getQnAList(productNo);
		System.out.println(qList);
		return new Gson().toJson(qList);
	}

	// QnA 상세+페이징
	@GetMapping("/detail")
	public ModelAndView qnaDetail(ModelAndView mv, @ModelAttribute QnA qna) {
		try {
			int result = qService.selectByproductNo(qna.getProductNo());
			User user = uService.getUserInfo(qna.getUserNo());
			if (result > 0) {
				if (qna.getUserNo() > 0) {
					mv.addObject("qna", qna);
					mv.addObject("productNo", qna.getProductNo());
					mv.addObject("writer", user.getUserId());
					mv.setViewName("qna/detail");
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
}
