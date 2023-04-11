package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pService;
	
	// 상품 목록 및 갯수 출력
	@GetMapping("shopping/list")
	public String selectProductList(Model model) {
		try {
			List<Product> pList = pService.getProductList();
			int pCount = pService.getProductCount();
			if(pList != null) {
				model.addAttribute("pList", pList);
				model.addAttribute("pCount", pCount);
				return "shopping/test";			} else {
				model.addAttribute("msg", "오류");
				return "error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
	}
}
