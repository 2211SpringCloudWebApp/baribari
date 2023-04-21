package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.product.domain.Review;
import com.kh.baribari.product.repository.ReviewRepository;

@Repository
public class ReviewRepositoryLogic implements ReviewRepository {

	@Override
	public List<Review> getReviewList(SqlSession session, int productNo) {
		List<Review> rList = session.selectList("ReviewMapper.getReviewList", productNo);
		return rList;
	}

	@Override
	public int registerReview(SqlSession session, Review review) {
		int result = session.insert("ReviewMapper.registerReview", review);
		// 첨부 사진이 있을 경우 후기사진테이블에 사진 등록
		if(review.getReviewPic1() != null) {
			// reviewNo에 현재 시퀀스값 등록
			int reviewNo = session.selectOne("ReviewMapper.getReviewNo");
			review.setReviewNo(reviewNo);
			session.insert("ReviewMapper.registerReviewPictures", review);
		}
		return result;
	}

	@Override
	public int getReviewCount(SqlSession session, int productNo) {
		int reviewCount = session.selectOne("ReviewMapper.getReviewCount", productNo);
		return reviewCount;
	}

}
