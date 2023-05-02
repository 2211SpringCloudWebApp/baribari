package com.kh.baribari.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.baribari.product.service.PaymentService;

@Controller
@RequestMapping("payment")
public class PaymentController {
	@Autowired
	private PaymentService pService;
	
	@PostMapping("/send")
	public void sendPaymentInfo() {
		
	}
	

}
