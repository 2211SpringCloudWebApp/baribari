package com.kh.baribari.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService oService;
	
	@PostMapping("/order")
	public void setOrder(@ModelAttribute Order order) {
		int result = oService.setOrder(order);
	}
}
