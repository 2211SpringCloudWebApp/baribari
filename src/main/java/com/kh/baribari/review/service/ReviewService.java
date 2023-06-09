package com.kh.baribari.review.service;

import java.util.List;

import com.kh.baribari.review.domain.Review;

public interface ReviewService {
	/**
	 * 상품에 대한 후기 목록
	 * @param int
	 * @return List<Review>
	 */
	public List<Review> getReviewList(int productNo);

	/**
	 * 상품에 대한 후기 등록
	 * @param Review
	 * @return int
	 */
	public int registerReview(Review review);

	/**
	 * 상품에 대한 후기 갯수
	 * @param int
	 * @return int
	 */
	public int getReviewCount(int productNo);

	/**
	 * 상품에 대한 후기 삭제
	 * @param Review
	 * @return int
	 */
	public int removeReview(Review review);

	/**
	 * 상품에 대한 후기
	 * @param Review
	 * @return Review
	 */
	public Review getReview(Review review);
	
	/**
	 * 모든 후기 목록
	 * @return List<Review>
	 */
	public List<Review> getReviewList();
}
