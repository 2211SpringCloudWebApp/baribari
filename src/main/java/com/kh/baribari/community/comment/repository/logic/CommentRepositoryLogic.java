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
		System.out.println("댓글등록 최종접근");
		return session.insert("CommentMapper.commentRegister",comment);
	}

	@Override
	public List<Comment> commentShow(SqlSession session, Integer communityNo) {
		return session.selectList("CommentMapper.commentShow",communityNo);
	}

	@Override
	public int commentDelete(SqlSession session, Integer commentNo) {
		return session.delete("CommentMapper.commentDelete",commentNo);
	}

	@Override
	public int registerReComment(SqlSession session, Comment rcomment) {
		return session.insert("CommentMapper.registerReComment",rcomment);
	}

	@Override
	public List<Comment> reCommentShow(SqlSession session, Integer commentNo) {
		return session.selectList("CommentMapper.reCommentShow",commentNo);
	}

	@Override
	public int reCommentDelete(SqlSession session, Integer commentNo) {
		return session.delete("CommentMapper.reCommentDelete",commentNo);
	}

}
