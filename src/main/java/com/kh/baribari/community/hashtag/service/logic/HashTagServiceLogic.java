package com.kh.baribari.community.hashtag.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.domain.HashTag;
import com.kh.baribari.community.hashtag.repository.HashTagRepository;
import com.kh.baribari.community.hashtag.service.HashTagService;

@Service
public class HashTagServiceLogic implements HashTagService{
	@Autowired
	private HashTagRepository Hrepository;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<HashTag> getHashTag(int boardNo) {
		return Hrepository.getHashTag(session, boardNo);
	}
	
	@Override
	public int registerHashTag(HashTag hTag) {
		return Hrepository.registerHashTag(session, hTag);
	}
	
	@Override
	public int deleteHashTag(HashTag hTag) {
		return Hrepository.deleteHashTag(session, hTag);
	}
}
