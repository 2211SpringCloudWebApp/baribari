package com.kh.baribari.product.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.repository.OrderRepository;
import com.kh.baribari.product.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private SqlSession session;
	@Autowired
	private OrderRepository oRepository;
	
	@Override
	public int setOrder(Order order) {
		return oRepository.setOrder(session, order);
	}
}
