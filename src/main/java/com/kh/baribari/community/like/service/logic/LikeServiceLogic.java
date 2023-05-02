package com.kh.baribari.community.like.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.domain.Like;
import com.kh.baribari.community.like.repository.LikeRepository;
import com.kh.baribari.community.like.service.LikeService;

@Service
public class LikeServiceLogic implements LikeService{
	
	@Autowired
	private LikeRepository lrepository;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int showLike(Like like) {
		return lrepository.showLike(session, like);
	}

	@Override
	public int registerLike(Like like) {
		return lrepository.registerLike(session, like);
	}

	@Override
	public int deleteLike(Like like) {
		return lrepository.deleteLike(session, like);
	}

	@Override
	public int testLike(Like like) {
		return lrepository.testLike(session, like);
	}

}
