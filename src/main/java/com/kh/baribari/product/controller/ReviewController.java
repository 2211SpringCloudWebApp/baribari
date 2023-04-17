package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.baribari.product.domain.Review;
import com.kh.baribari.product.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService rService;
	
	// 상품 후기 등록
	@PostMapping("review/register")
	@ResponseBody
	public String registerReview(@ModelAttribute Review review) {
		try {
			int result = rService.registerReview(review);
			if (result > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	// 상품 후기 목록 출력
	@GetMapping("review/list")
	@ResponseBody
	public String getReviewList(Integer productNo) {
		List<Review> rList = rService.getReviewList(productNo);
		return new Gson().toJson(rList);
	}
	
}
