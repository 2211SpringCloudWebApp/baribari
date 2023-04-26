package com.kh.baribari.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.product.service.ReviewService;

@Controller
public class Home {
	@Autowired
	private ProductService pService;
	@Autowired
	private ReviewService rService;
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mv) {
		// 공지&이벤트 가져오기
		
		// 인기상품 가져오기
		List<Product> pList = pService.getProductListBySales();
		// 후기 가져오기
		
		// 커뮤니티 가져오기
		
		mv.addObject("pList", pList);
		mv.setViewName("index");
		return mv;
	}
}
