package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.product.domain.Review;

public interface ReviewRepository {
	
	/**
	 * 상품에 대한 후기 목록
	 * @param SqlSession
	 * @param int
	 * @return List<Review>
	 */
	public List<Review> getReviewList(SqlSession session, int productNo);
	
	/**
	 * 상품에 대한 후기 등록
	 * @param SqlSession
	 * @param Review
	 * @return int
	 */
	public int registerReview(SqlSession session, Review review);

	/**
	 * 상품에 대한 후기 갯수
	 * @param SqlSession
	 * @param int
	 * @return int
	 */
	public int getReviewCount(SqlSession session, int productNo);

	/**
	 * 상품에 대한 후기 삭제
	 * @param SqlSession
	 * @param Review
	 * @return int
	 */
	public int removeReview(SqlSession session, Review review);

	/**
	 * 상품에 대한 후기
	 * @param SqlSession
	 * @param Review
	 * @return Review
	 */
	public Review getReview(SqlSession session, Review review);

	/**
	 * 모든 후기 목록
	 * @param SqlSession
	 * @return List<Review>
	 */
	public List<Review> getReviewList(SqlSession session);

}
