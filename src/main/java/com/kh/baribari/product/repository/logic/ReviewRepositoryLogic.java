package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.baribari.product.domain.Review;
import com.kh.baribari.product.repository.ReviewRepository;

@Repository
public class ReviewRepositoryLogic implements ReviewRepository {
	@Override
	public List<Review> getReviewList(SqlSession session, int productNo) {
		return session.selectList("ReviewMapper.getReviewList", productNo);
	}

	@Override
	@Transactional
	public int registerReview(SqlSession session, Review review) {
		int result1 = session.insert("ReviewMapper.registerReview", review);
		// 첨부 사진이 있을 경우 후기사진테이블에 사진 등록
		int result2 = 0;
		if (review.getReviewPic1() != null) {
			// REVIEW_TBL에서 현재 시퀀스값을 가져와서 reviewNo에 저장
			int reviewNo = session.selectOne("ReviewMapper.getReviewNo");
			review.setReviewNo(reviewNo);
			// REVIEW_PIC_TBL에 저장
			result2 = session.insert("ReviewMapper.registerReviewPictures", review);
		}
		// 성공 시 1 또는 2 반환
		return result1 + result2;
	}

	@Override
	public int getReviewCount(SqlSession session, int productNo) {
		return session.selectOne("ReviewMapper.getReviewCount", productNo);
	}

	@Override
	public int removeReview(SqlSession session, Review review) {
		return session.delete("ReviewMapper.removeReview", review);
	}

	@Override
	public Review getReview(SqlSession session, Review review) {
		return session.selectOne("ReviewMapper.getReview", review);
	}

	@Override
	public List<Review> getReviewList(SqlSession session) {
		return session.selectList("ReviewMapper.getAllReviewList");
	}
}
