package com.kh.baribari.community.hashtag.service;

import java.util.List;

import com.kh.baribari.community.domain.HashTag;

public interface HashTagService {
	/**
	 * 헤시태그를 가져옴
	 * @return List<HashTag>
	 */
	public List<HashTag> getHashTag(int boardNo);
	
	/**
	 * 해시태그 등록
	 * @param hTag
	 * @return int
	 */
	public int registerHashTag(HashTag hTag);
	
	/**
	 * 해시태그 삭제
	 * @param hTag
	 * @return int
	 */
	public int deleteHashTag(HashTag hTag);
}
