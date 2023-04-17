package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.product.domain.Review;

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
}
