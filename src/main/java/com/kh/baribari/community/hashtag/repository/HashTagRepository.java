package com.kh.baribari.community.hashtag.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.community.domain.HashTag;

public interface HashTagRepository {
	/**
	 * 해시태그 가져오기
	 * @param communityNo
	 * @return List<HashTag>
	 */
	public List<HashTag> getHashTag(SqlSession session,int boardNo);
	
	/**
	 * 해시태그 등록
	 * @param session
	 * @param hTag
	 * @return
	 */
	public int registerHashTag(SqlSession session, HashTag hTag);
	
	/**
	 * 해시태그 삭제
	 * @param session
	 * @param hTag
	 * @return
	 */
	public int deleteHashTag(SqlSession session, HashTag hTag);
}
