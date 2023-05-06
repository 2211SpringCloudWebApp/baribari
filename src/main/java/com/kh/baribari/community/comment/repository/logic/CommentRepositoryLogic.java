package com.kh.baribari.community.comment.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.comment.repository.CommentRepository;
import com.kh.baribari.community.domain.Comment;

@Repository
public class CommentRepositoryLogic implements CommentRepository{

	@Override
	public int commentRegister(SqlSession session, Comment comment) {
		return session.insert("CommentMapper.commentRegister",comment);
	}

	@Override
	public List<Comment> commentShow(SqlSession session, Integer communityNo) {
		return session.selectList("CommentMapper.commentShow",communityNo);
	}

}
