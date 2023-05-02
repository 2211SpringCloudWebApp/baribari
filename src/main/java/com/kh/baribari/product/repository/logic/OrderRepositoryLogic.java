package com.kh.baribari.product.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.repository.OrderRepository;

@Repository
public class OrderRepositoryLogic implements OrderRepository {
	@Override
	@Transactional
	public int setOrder(SqlSession session, Order order) {
		int result1 = session.insert("OrderMapper.setOrderInfo", order);
		int result2 = session.insert("OrderMapper.setOrderDetailInfo", order);
		return result1 + result2;
	}
}