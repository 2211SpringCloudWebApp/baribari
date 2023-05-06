package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.domain.Product;

public interface OrderRepository {

	int setOrder(SqlSession session, Order order);

	/**
	 * 판매자가 판매하고 있는 상품의 주문 목록
	 * @param SqlSession
	 * @param int
	 * @return List<Order>
	 */
	List<Order> getOrderList(SqlSession session, int userNo);

}
