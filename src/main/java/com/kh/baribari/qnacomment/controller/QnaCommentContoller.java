package com.kh.baribari.qnacomment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qnacomment.domain.QnaComment;
import com.kh.baribari.qnacomment.service.QnaCommentService;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("/qcomment")
public class QnaCommentContoller {
	@Autowired
	private QnaCommentService qService;
	@Autowired
	private UserService uService;

	// 댓글 작성
	@ResponseBody
	@GetMapping("/register")
	public int qcommentRegister(@RequestParam(value = "productNo", required = false) Integer productNo,
			@RequestParam(value= "qnaNo", required = false) Integer qnaNo,
			@RequestParam(value = "userNo", required = false) Integer userNo,
			@RequestParam(value = "commentContent", required = false) String commentContent) throws Exception {
		QnaComment qcomment = new QnaComment();
		QnA qna = new QnA();
		qcomment.setQnaNo(qna.getQnaNo());
		qcomment.setProductNo(productNo);
		qcomment.setUserNo(userNo);
		qcomment.setCommentContent(commentContent);
		int result = qService.qcommentRegister(qcomment);
		return result;
	}

	// 댓글 뷰
	@ResponseBody
	@GetMapping("/view")
	public String commentView(
			@RequestParam(value="productNo", required = false) Integer productNo) {
		return "/shopping/detail?productNo="+productNo;
	}

	// 댓글 삭제
	@ResponseBody
	@GetMapping("/delete")
	public String deleteQComment(@RequestParam(value = "commentNo", required = false) Integer commentNo,
			@RequestParam(value = "productNo", required = false) Integer productNo) {
		int result = qService.deleteQComment(commentNo);
		if (result > 0) {
			return "redirect:/shopping/detail?productNo=" + productNo;
		} else {
			return "common/error";
		}
	}
}
