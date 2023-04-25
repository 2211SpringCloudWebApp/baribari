package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.product.domain.Review;
import com.kh.baribari.product.repository.ReviewRepository;
import com.kh.baribari.product.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewRepository rRepository;
	@Autowired
    private SqlSession session;

	@Override
	public List<Review> getReviewList(int productNo) {
		return rRepository.getReviewList(session, productNo);
	}

	@Override
	public int registerReview(Review review) {
		return rRepository.registerReview(session, review);
	}

	@Override
	public int getReviewCount(int productNo) {
		return rRepository.getReviewCount(session, productNo);
	}

	@Override
	public int removeReview(Review review) {
		return rRepository.removeReview(session, review);
	}

	@Override
	public Review getReview(Review review) {
		return rRepository.getReview(session, review);
	}
}
