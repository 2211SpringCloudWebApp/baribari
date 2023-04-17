package com.kh.baribari.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.baribari.common.FileUpload;
import com.kh.baribari.product.domain.Review;
import com.kh.baribari.product.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService rService;
	@Autowired
	@Qualifier("fileUpload")
	private FileUpload fileUpload;
	
	// 상품 후기 등록
	@PostMapping("review/register")
	@ResponseBody
	public String registerReview(
			@RequestParam(value = "uploadFile", required = false) MultipartFile multi
			, @ModelAttribute Review review
			, HttpServletRequest request) {
		Map<String, String> fileInfo = null;
		try {
			String path = "shopping/review";
			fileInfo = fileUpload.saveFile(multi, request, path);
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
