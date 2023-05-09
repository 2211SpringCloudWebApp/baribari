package com.kh.baribari.community.comment.controller;

import java.util.ArrayList;
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
	
	//대댓글등록
	@ResponseBody
	@GetMapping("registerReComment")
	public int registerReComment(
			@RequestParam(value="communityNo",required = false) Integer communityNo
			,@RequestParam(value="commentNo",required = false) Integer commentNo
			,@RequestParam(value="replyContent",required = false) String replyContent
			,@RequestParam(value="loginUserNo",required = false) Integer loginUserNo
			)throws Exception {
		Comment rcomment = new Comment();
		rcomment.setCommunityNo(communityNo);
		rcomment.setParentsCommentNo(commentNo);
		rcomment.setCommentContent(replyContent);
		rcomment.setUserNo(loginUserNo);
		int result = cService.registerReComment(rcomment);
		return result;
	}
	
	//댓글, 대댓글 출력하기
	@ResponseBody
	@GetMapping("commentShow")
	public List<Comment> commentShow(
			@RequestParam(value="communityNo",required = false) Integer communityNo
			)throws Exception {
		
		List<Comment> slist = new ArrayList<Comment>(); // 댓글 + 대댓글을 위한 리스트
		List<Comment> rlist = null; // 대댓글을 저장할 리스트
		List<Comment> clist = cService.commentShow(communityNo);
		
		//System.out.println(clist);
		
		for(Comment comment : clist) { // 리스트만큼 반복함.
			slist.add(comment);
			rlist =  cService.reCommentShow(comment.getCommentNo());
			if(rlist != null) {
				for(Comment reComment : rlist) {
					slist.add(reComment);
				}
			}
		}
		return slist;
	}
	
	//대댓글 출력하기
	public List<Comment> ReCommentShow(
			@RequestParam(value="commentNo",required = false) Integer commentNo
			)throws Exception{
		List<Comment> rlist = cService.reCommentShow(commentNo);
		return rlist;
	}
	
	//댓글, 대댓글 삭제
	@ResponseBody
	@GetMapping("commentDelete")
	public int commentDelete(
			@RequestParam(value="commentNo",required = false) Integer commentNo
			,@RequestParam(value="check",required = false) Integer check
			) {
		if(check == 0) { // 댓글일경우
			return cService.commentDelete(commentNo);
		}else{ // 대댓글일경우
			return cService.reCommentDelete(commentNo);
		}
	}
}
