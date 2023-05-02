package com.kh.baribari.community.like.service;

import com.kh.baribari.community.domain.Like;

public interface LikeService {

	/**
	 * 좋아요 조회
	 * @param like
	 * @return int
	 */
	int showLike(Like like);

	/**
	 * 좋아요 등록
	 * @param like
	 * @return int
	 */
	int registerLike(Like like);

	/**
	 * 좋아요 삭제
	 * @param like
	 * @return int
	 */
	int deleteLike(Like like);

	/**
	 * 좋아요 검사
	 * @param like
	 * @return int
	 */
	int testLike(Like like);
}
