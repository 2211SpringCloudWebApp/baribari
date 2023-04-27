package com.kh.baribari.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.kh.baribari.common.FileInfo;
import com.kh.baribari.review.domain.Review;
import com.kh.baribari.review.service.ReviewService;

@Controller
@RequestMapping("review")
public class ReviewController {
	@Autowired
	private ReviewService rService;
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileUpload;
	
	// 상품 후기 등록
	@PostMapping("/register")
	@ResponseBody
	public String registerReview(
			@RequestParam(value = "fileList", required = false) List<MultipartFile> fList
			, @ModelAttribute Review review
			, HttpServletRequest request
			) throws Exception {
		Map<String, String> fMap = new HashMap<String, String>();
    	// 파일 경로
        String path = "shopping/review";
        int i = 1;
        // 첨부파일이 있을 경우 파일 저장
        if (fList != null) {
        	for (MultipartFile file : fList) {
        		Map<String, String> fileInfo = fileUpload.saveFile(file, request, path);
        		for (String k : fileInfo.keySet()) {
        			String key = "file" + i;
        			String value = fileInfo.get(k);
        			fMap.put(key, value);
        			if (i == 1) {
        				review.setReviewPic1(value);
        			} else if (i == 2) {
        				review.setReviewPic2(value);
        			} else if (i == 3) {
        				review.setReviewPic3(value);
        			} else if (i == 4) {
        				review.setReviewPic4(value);
        			} else if (i == 5) {
        				review.setReviewPic5(value);
        			}
        		}
        		i++;
        	}
		}
        int result = rService.registerReview(review);
        if (result > 0) {
            return "1";
        } else {
            return "0";
        }
	}
	
	// 상품 후기 삭제
	@PostMapping("/remove")
	@ResponseBody
	public String removeReview(@ModelAttribute Review reviewParam, HttpServletRequest request) throws Exception {
		// 후기 정보 가져오기 → 사진이 있을 경우 사진 물리적 삭제 → 후기 삭제 (CASCADE로 인해 후기 사진 테이블의 자료도 자동 삭제)
		// 사진 정보를 가져오기 위해 review정보 가져오기
		Review review = rService.getReview(reviewParam);
		String path = "shopping/review";
		// 사진이 있을 경우 삭제
		if (review.getReviewPic1() != null && !review.getReviewPic1().isEmpty()) {
		    fileUpload.deleteFile(request, review.getReviewPic1());
		}
		if (review.getReviewPic2() != null && !review.getReviewPic2().isEmpty()) {
			fileUpload.deleteFile(request, review.getReviewPic2());
		}
		if (review.getReviewPic3() != null && !review.getReviewPic3().isEmpty()) {
			fileUpload.deleteFile(request, review.getReviewPic3());
		}
		if (review.getReviewPic4() != null && !review.getReviewPic4().isEmpty()) {
			fileUpload.deleteFile(request, review.getReviewPic4());
		}
		if (review.getReviewPic5() != null && !review.getReviewPic5().isEmpty()) {
			fileUpload.deleteFile(request, review.getReviewPic5());
		}
		// 후기 삭제
		int result = rService.removeReview(review);
		if (result > 0) {
            return "1";
        } else {
            return "0";
        }
	}
	
	// 상품 후기 목록 출력
	@GetMapping("/list")
	@ResponseBody
	public String getReviewList(Integer productNo) {
		List<Review> rList = rService.getReviewList(productNo);
		return new Gson().toJson(rList);
	}
}
