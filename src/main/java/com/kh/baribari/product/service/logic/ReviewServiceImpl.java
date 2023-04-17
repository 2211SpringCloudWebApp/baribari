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
		List<Review> rList = rRepository.getReviewList(session, productNo);
		return rList;
	}

	@Override
	public int registerReview(Review review) {
		int result = rRepository.registerReview(session, review);
		return result;
	}
	

}
