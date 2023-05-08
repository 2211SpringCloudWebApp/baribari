package com.kh.baribari.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.CartService;
import com.kh.baribari.product.service.OrderService;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderService oService;
	@Autowired
	private CartService cService;
	@Autowired
	private ReturnUser returnUser;
	@Autowired
	private UserService uService;
	@Autowired
	private ProductService pService;
	
	@PostMapping("/save")
	public void setOrder(Authentication authentication, Order order) {
		User user = returnUser.returnUser(authentication);
		List<Cart> cList = cService.getCartList(user.getUserNo());
		System.out.println(cList);
		// 주문 정보 DB에 저장
	}
	
	@GetMapping("/")
	public ModelAndView getOrder(Authentication authentication, ModelAndView mv) {
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
		mv.addObject("cList", cList);
		mv.setViewName("shopping/order");
		return mv;
	}
	
	public ModelAndView sendOrderInfo(ModelAndView mv) {
		return mv;
	}
	
	// 해당 주문에 대한 주문정보
	@PostMapping("/info")
	public ModelAndView getOrder(ModelAndView mv, int orderNo) {
		return mv;
	}
}
