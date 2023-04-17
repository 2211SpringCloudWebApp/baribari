package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileUpload;
import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;

@Controller
@RequestMapping("shopping")
public class ProductController {
	
	@Autowired
	private ProductService pService;
	@Autowired
	@Qualifier("fileUpload")
	private FileUpload fileUpload;
	
	// 상품 목록 및 갯수 출력
	@GetMapping("/list")
	public ModelAndView getProductList(
			ModelAndView mv
			, @RequestParam(value = "category", required = false, defaultValue = "All") String productCategory
			, @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			) {
		try {
			// 상품 총 갯수
			int pCount = pService.getProductCount(productCategory);
			// PageInfo 매개변수: 현재페이지 (RequestParam), 전체 게시글 수 (mapper), 페이지 당 게시글 수
			PageInfo pi = new PageInfo(currentPage, pCount, 20); 
			// 상품 목록
			List<Product> pList = pService.getProductList(productCategory, pi);
			
			if(pList != null) {
				mv.addObject("pList", pList);
				mv.addObject("pCount", pCount);
				mv.addObject("pi", pi);
				mv.setViewName("shopping/list");
				return mv;
			} else {
				mv.addObject("msg", "오류").setViewName("error");
				return mv;
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}
	
	// 상품 검색 목록 및 갯수 출력
	public ModelAndView getProductSearchList(
			ModelAndView mv
			, @RequestParam(value = "category", required = false, defaultValue = "All") String productCategory
			, @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			, @ModelAttribute Search search
			) {
		try {
			return mv;
		} catch (Exception e) {
			return mv;
		}
	}
	
	// 상품 상세 페이지 (상품 정보, 추천 상품 목록)
	@GetMapping("/detail")
	public ModelAndView getProductDetail(ModelAndView mv, int productNo) {
		try {
			// 상품 정보
			Product product = pService.getProductDetail(productNo);
			// 상품 분류에 따른 추천 상품 목록
			List<Product> pList = pService.getProductRecommendList(product.getProductCategory());
			
			mv.addObject("product", product);
			mv.addObject("pList", pList);
			mv.setViewName("shopping/detail");
			return mv;
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("error");
			return mv;
		}
	}
	
}
