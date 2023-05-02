package com.kh.baribari.community.like.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.community.domain.Like;

public interface LikeRepository {

	int showLike(SqlSession session, Like like);

	int registerLike(SqlSession session, Like like);

	int deleteLike(SqlSession session, Like like);

	int testLike(SqlSession session, Like like);

}
