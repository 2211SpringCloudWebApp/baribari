package com.kh.baribari.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.baribari.product.domain.QnA;
import com.kh.baribari.product.service.QnAService;
import com.kh.baribari.user.domain.User;

@Controller
public class QnAController {
	@Autowired
	private QnAService qService;

	// QnA 등록
	@PostMapping("qna/register")
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
	@GetMapping("qna/list")
	@ResponseBody
	public String getQnAList(Integer productNo) {
		List<QnA> qList = qService.getQnAList(productNo);
		System.out.println(qList);
		return new Gson().toJson(qList);
	}
}
