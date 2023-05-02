package com.kh.baribari.community.hashtag.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.domain.HashTag;
import com.kh.baribari.community.hashtag.repository.HashTagRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class HashTagRepositoryLogic implements HashTagRepository{
	@Override
	public List<HashTag> getHashTag(SqlSession session,int boardNo) {
		return session.selectList("HashtagMapper.getHashTag", boardNo);
	}

	@Override
	public int registerHashTag(SqlSession session, HashTag hTag) {
		return session.insert("HashtagMapper.registerHashTag", hTag);
	}
	
	@Override
	public int deleteHashTag(SqlSession session, HashTag hTag) {
		return session.delete("HashtagMapper.deleteHashTag",hTag);
	}
}
