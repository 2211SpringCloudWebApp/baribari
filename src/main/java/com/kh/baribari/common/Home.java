package com.kh.baribari.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.community.board.service.BoardService;
import com.kh.baribari.message.service.MessageService;
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
	private BoardService bService;
	@Autowired
	private MessageService mService;
	@Autowired
	private ReturnUser returnUser;

	@GetMapping("/")
	public ModelAndView home(ModelAndView mv, Authentication authentication) {
		// 공지&이벤트 가져오기
		List<Notice> nList;
		// 인기상품 가져오기
		List<Product> pList = pService.getProductListBySales();
		// MD추천상품 가져오기
		List<Product> mList = pService.getMdRecommendProductList();
		// 후기 가져오기
		List<Review> rList = rService.getReviewList();
		// 커뮤니티 가져오기 - 0: 추천수 순, 1: 날짜 순, 2: 조회수 순
		
		mv.addObject("pList", pList); // 인기상품
		mv.addObject("mList", mList); // MD추천상품
		mv.addObject("rList", rList); // 후기
		mv.setViewName("index");
		return mv;
	}
}
