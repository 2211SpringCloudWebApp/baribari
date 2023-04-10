package com.kh.baribari.product.controller;

import java.util.ArrayList;
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
	
	// 상품리스트 가져오기
	@GetMapping("shopping/list")
	public String selectProductList(Model model) {
		
		List<Product> pList = new ArrayList<>();
		try {
			pList = pService.selectProductList();
			if(pList != null) {
				model.addAttribute("pList", pList);
				System.out.println(pList);
				return "shopping/list";			} else {
				model.addAttribute("msg", "오류");
				return "error";
			}
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error";
		}
		
	}
	
}
