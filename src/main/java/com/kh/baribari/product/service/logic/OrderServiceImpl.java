package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.product.domain.Cart;
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
	public int setOrder(List<Cart> cList) {
		return oRepository.setOrder(session, cList);
	}

	@Override
	public List<Order> getOrderList(int userNo) {
		return oRepository.getOrderList(session, userNo);
	}
}
