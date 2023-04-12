package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	// 상품 목록 및 갯수 출력
	@GetMapping("shopping/list")
	public String getProductList(
			Model model
			, @RequestParam(value = "category", required = false, defaultValue = "All") String productCategory
			, @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			) {
		try {
			// 상품 총 갯수
			int pCount = pService.getProductCount(productCategory);
			// PageInfo 매개변수: 현재페이지 (RequestParam), 전체 게시글 수 (mapper), 페이지 당 게시글 수
			PageInfo pi = new PageInfo(currentPage, pCount, 20); 
			List<Product> pList = pService.getProductList(productCategory, pi);
			if(pList != null) {
				model.addAttribute("pList", pList);
				model.addAttribute("pCount", pCount);
				model.addAttribute("pi", pi);
				return "shopping/list";			} else {
				model.addAttribute("msg", "오류");
				return "error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}
	
	@GetMapping("shopping/detail")
	public void getProductDetail() {
		
	}
	
}
