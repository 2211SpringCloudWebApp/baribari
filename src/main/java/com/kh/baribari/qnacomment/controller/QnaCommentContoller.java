package com.kh.baribari.qnacomment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.qna.domain.QnA;
import com.kh.baribari.qnacomment.service.QnaCommentService;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("qComment")
public class QnaCommentContoller {
	@Autowired
	private QnaCommentService qService;
	@Autowired
	private UserService uService;

	// 댓글 작성
	@ResponseBody
	@PostMapping("/register")
	public String qcommentRegister(
			@RequestParam(value= "qnaNo", required = false) Integer qnaNo,
			@RequestParam(value = "userNo", required = false) Integer userNo,
			@RequestParam(value = "commentContent", required = false) String commentContent) throws Exception {
		QnA qcomment = new QnA();
		QnA qna = new QnA();
		qcomment.setQnaNo(qnaNo);
		qcomment.setUserNo(userNo);
		qcomment.setCommentContent(commentContent);
		System.out.println(qcomment);
		int result = qService.qcommentRegister(qcomment);
		if (result > 0) {
            return "1";
        } else {
            return "0";
        }
	}

	// 댓글 뷰
	@ResponseBody
	@GetMapping("/view")
	public List<QnA> commentView(@RequestParam(value="productNo", required = false) Integer productNo) throws Exception {
		List<QnA> qList = qService.getQnAList(productNo);
		List<QnA> qcList = null;
		List<QnA> sList = new ArrayList<QnA>();
		for(QnA qna : qList) {
			sList.add(qna);
			qcList = qService.commentShow(qna.getQnaNo());
			if(qcList != null) {
				for (QnA qnaComment : qcList) {
					sList.add(qnaComment);
				}
			}
		}
		return sList;
	}

	// 댓글 삭제
	@ResponseBody
	@PostMapping("/delete")
	public String deleteQComment(@RequestParam(value = "commentNo", required = false) Integer commentNo,
			@RequestParam(value = "userNo", required = false) Integer userNo) {
		int result = qService.deleteQComment(commentNo);
		if (result > 0) {
            return "1";
        } else {
            return "0";
        }
	}
}
