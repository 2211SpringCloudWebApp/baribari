package com.kh.baribari.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kh.baribari.product.service.PaymentService;

@Controller
public class PaymentController {
	@Autowired
	private PaymentService pService;

}
