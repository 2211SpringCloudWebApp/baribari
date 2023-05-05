package com.kh.baribari.community.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.baribari.community.comment.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService cService;
	
	@GetMapping("commentRegister")
	public int commentRegister(
			
			) {
		return 0;
	}
}
