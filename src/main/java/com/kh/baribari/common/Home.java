package com.kh.baribari.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.community.board.repository.BoardRepository;
import com.kh.baribari.notice.domain.Notice;
import com.kh.baribari.notice.service.NoticeService;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.review.domain.Review;
import com.kh.baribari.review.service.ReviewService;

@Controller
public class Home {
	@Autowired
	private NoticeService nService;
	@Autowired
	private ProductService pService;
	@Autowired
	private ReviewService rService;
	@Autowired
	private BoardRepository bService;
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mv) {
		// 공지&이벤트 가져오기
		List<Notice> nList;
		// 인기상품 가져오기
		List<Product> pList = pService.getProductListBySales();
		// 후기 가져오기
		List<Review> rList = rService.getReviewList();
		// 커뮤니티 가져오기
		
		mv.addObject("pList", pList);
		mv.addObject("rList", rList);
		mv.setViewName("index");
		return mv;
	}
}
