package com.kh.baribari.community.comment.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.comment.repository.CommentRepository;
import com.kh.baribari.community.comment.service.CommentService;

@Service
public class CommentServiceLogic implements CommentService{
	
	@Autowired
	private CommentRepository crepositery;
	
	@Autowired
	private SqlSession session;
}
