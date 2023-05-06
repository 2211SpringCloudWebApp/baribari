package com.kh.baribari.community.comment.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.comment.repository.CommentRepository;
import com.kh.baribari.community.comment.service.CommentService;
import com.kh.baribari.community.domain.Comment;

@Service
public class CommentServiceLogic implements CommentService{
	
	@Autowired
	private CommentRepository crepositery;
	
	@Autowired
	private SqlSession session;

	@Override
	public int commentRegister(Comment comment) {
		return crepositery.commentRegister(session, comment);
	}

	@Override
	public List<Comment> commentShow(Integer communityNo) {
		return crepositery.commentShow(session, communityNo);
	}

	@Override
	public int commentDelete(Integer commentNo) {
		return crepositery.commentDelete(session,commentNo);
	}

	@Override
	public int registerReComment(Comment rcomment) {
		return crepositery.registerReComment(session,rcomment);
	}

	@Override
	public List<Comment> reCommentShow(Integer commentNo) {
		return crepositery.reCommentShow(session, commentNo);
	}

	@Override
	public int reCommentDelete(Integer commentNo) {
		return crepositery.reCommentDelete(session, commentNo);
	}
}
