package com.kh.baribari.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.service.CartService;
import com.kh.baribari.product.service.OrderService;
import com.kh.baribari.product.service.ProductService;
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
	
	@PostMapping("/")
	public void setOrder(Order order) {
		System.out.println(order);
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
