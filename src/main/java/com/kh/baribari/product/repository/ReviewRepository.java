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

}
