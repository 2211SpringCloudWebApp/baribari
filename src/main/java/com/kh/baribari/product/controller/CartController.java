package com.kh.baribari.product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.CartService;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("cart")
public class CartController {
	@Autowired
	private CartService cService;
	@Autowired
	private ProductService pService;
	@Autowired
	private UserService uService;
	@Autowired
	private ReturnUser returnUser;

	// 장바구니에 상품 추가
	@PostMapping("/add")
	@ResponseBody
	public String addToCart(Cart cart) {
		int result = cService.addToCart(cart);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	// 장바구니 목록
	@GetMapping("/list")
	public ModelAndView getCartList(Authentication authentication, ModelAndView mv) throws IOException {
		// 사용자 정보
		User user = returnUser.returnUser(authentication);
		List<Address> aList = uService.selectAddressList(user);
		// 장바구니 목록
		List<Cart> cList = cService.getCartList(user.getUserNo());
		// 장바구니의 각 상품에 대한 정보
		for (Cart cart : cList) {
			int productNo = cart.getProductNo();
			Product product = pService.getProductDetail(productNo);
			cart.setProduct(product);
		}

		mv.addObject("user", user);
		mv.addObject("aList", aList);
		mv.addObject("pList", cList);
		mv.setViewName("shopping/cart");
		return mv;
	}

	// 장바구니에서 상품 제거
	@PostMapping("/remove")
	@ResponseBody
	public String removeFromCart(Cart cart) {
		int result = cService.removeFromCart(cart);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	
	// 상품 수량 변경
	@PostMapping("/update")
	@ResponseBody
	public String updateQuantity(Cart cart) {
		int result = cService.updateQuantity(cart);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
}
