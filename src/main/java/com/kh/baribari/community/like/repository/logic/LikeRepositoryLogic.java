package com.kh.baribari.community.like.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.domain.Like;
import com.kh.baribari.community.like.repository.LikeRepository;

@Repository
public class LikeRepositoryLogic implements LikeRepository{

	@Override
	public int showLike(SqlSession session, Like like) {
		return session.selectOne("LikeMapper.showLike",like);
	}

	@Override
	public int registerLike(SqlSession session, Like like) {
		return session.insert("LikeMapper.registerLike",like);
	}

	@Override
	public int deleteLike(SqlSession session, Like like) {
		return session.delete("LikeMapper.deleteLike",like);
	}

	@Override
	public int testLike(SqlSession session, Like like) {
		return session.selectOne("LikeMapper.testLike",like);
	}

}
