package com.kh.baribari.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.CartService;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;

@Controller
public class CartController {
	@Autowired
	private CartService cService;
	@Autowired
	private ProductService pService;

	// 장바구니에 상품 추가
	@PostMapping("/cart/add")
	@ResponseBody
	public String addToCart(@ModelAttribute Cart cart) {
		int result = cService.addToCart(cart);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 장바구니 목록
	@GetMapping("/cart/list")
	public ModelAndView getCartList(Authentication authentication, ModelAndView mv) throws IOException {
		// 사용자 정보
		User user = returnUser(authentication);
		// 장바구니 목록
		List<Cart> cList = cService.getCartList(user.getUserNo());
		// 장바구니의 각 상품에 대한 정보
		for (Cart cart : cList) {
			int productNo = cart.getProductNo();
			Product product = pService.getProductDetail(productNo);
			cart.setProduct(product);
		}

		mv.addObject("user", user);
		mv.addObject("pList", cList);
		mv.setViewName("shopping/cart/list");
		return mv;
	}

	// 장바구니에서 상품 제거
	@PostMapping("/cart/remove")
	@ResponseBody
	public String removeFromCart(Integer userNo, Integer productNo) {
		Cart cart = new Cart();
		cart.setUserNo(userNo);
		cart.setProductNo(productNo);
		int result = cService.removeFromCart(cart);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 세션에서 사용자 불러오기
	private User returnUser(Authentication authentication) {
		PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
		return userDetails.getUser();
	}
}
