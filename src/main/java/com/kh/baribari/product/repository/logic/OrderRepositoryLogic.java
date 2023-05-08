package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.repository.OrderRepository;

@Repository
public class OrderRepositoryLogic implements OrderRepository {
	@Override
	@Transactional
	public int setOrder(SqlSession session, List<Cart> cList) {
		int result1 = session.insert("OrderMapper.setOrderInfo", cList);
		int result2 = session.insert("OrderMapper.setOrderDetailInfo", cList);
		return result1 + result2;
	}

	@Override
	public List<Order> getOrderList(SqlSession session, int userNo) {
		return session.selectList("OrderMapper.getOrderList", userNo);
	}
}