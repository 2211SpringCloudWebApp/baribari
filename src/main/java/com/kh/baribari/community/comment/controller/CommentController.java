package com.kh.baribari.community.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.baribari.community.comment.service.CommentService;
import com.kh.baribari.community.domain.Comment;

@Controller
public class CommentController {

	@Autowired
	private CommentService cService;
	
	// 댓글등록
	@ResponseBody
	@GetMapping("commentRegister")
	public int commentRegister(
			@RequestParam(value="communityNo",required = false) Integer communityNo
			,@RequestParam(value="userNo",required = false) Integer userNo
			,@RequestParam(value="commentContent",required = false) String commentContent
			)throws Exception {
		
		Comment comment = new Comment();
		comment.setCommunityNo(communityNo);
		comment.setUserNo(userNo);
		comment.setCommentContent(commentContent);
		
		int result = cService.commentRegister(comment);
		return result;
	}
	
	//댓글 출력하기
	@ResponseBody
	@GetMapping("commentShow")
	public List<Comment> commentShow(
			@RequestParam(value="communityNo",required = false) Integer communityNo
			)throws Exception {
		
		List<Comment> clist = cService.commentShow(communityNo);
		return clist;
	}
}
