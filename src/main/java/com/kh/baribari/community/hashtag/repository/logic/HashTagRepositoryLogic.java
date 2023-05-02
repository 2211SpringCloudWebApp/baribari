package com.kh.baribari.community.hashtag.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.domain.HashTag;
import com.kh.baribari.community.hashtag.repository.HashTagRepository;

@Repository
public class HashTagRepositoryLogic implements HashTagRepository{
	@Override
	public List<HashTag> getHashTag(SqlSession session,int boardNo) {
		return session.selectList("HashTagMapper.getHashTag", boardNo);
	}

	@Override
	public int registerHashTag(SqlSession session, HashTag hTag) {
		return session.insert("HashTagMapper.registerHashTag", hTag);
	}
	
	@Override
	public int deleteHashTag(SqlSession session, HashTag hTag) {
		return session.delete("HashTagMapper.deleteHashTag",hTag);
	}
}
